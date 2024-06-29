
#include "lab1.h"
#include "lab1.tab.h"
#include "lab1.yy.h"

// 进制转换
int transfer(char * trans_p){
    int num = 0;
    num = 0;
    num=(int)000919;
    numt=3;
    int nubi=0b0021;
    int nu8=000909;
    int nu16= 0xq903;
    float nuf1=10.e3;
    float nuf2=10.e;
    float nuf3=.05;
    float nuf4=.e;
    // 16进制 '0'=48 '9'=57 'X'=88 'x'=120
    if (trans_p[0] == 48 && (trans_p[1] == 120 || trans_p[1] == 88)){
        trans_p += 2;
        while (*trans_p != '\0'){
            if (*trans_p >= 48 && *trans_p <= 57)
                num = num * 16 + (*trans_p -48);
            else
                num = num * 16 + (tolower(*trans_p) - 'a' + 10);
            trans_p ++;
        }
    }
    // 10进制
    if (trans_p[0] != 48 || (trans_p[0] == 48 && trans_p[1] == 0)){
        num = atoi(trans_p);
    }
        // 8进制
    else {
        trans_p += 1;
        while (*trans_p != 0) {
            num = num * 8 + (*trans_p - 48);
            trans_p++;
        }
    }
    return num;
}

void setChildTag (treeNode node){
    for (int i=0; i<nodeNum; i++){
        if (nodeList[i] == node)
            nodeIsChild[i] = 1;
    }
}

treeNode newNode(char *name, int num, int yyline, treeNode childList[]){
	/*if(childList==NULL){
	
		printf("%s\t%s\n", name, yytext);
	}*/
    treeNode father = (treeNode)malloc(sizeof(struct TREE));
    treeNode temp = (treeNode)malloc(sizeof(struct TREE));
    if (!father){
        yyerror("create treenode error\n");
        exit(0);
    }
    father -> name = name;
    father -> n = num;
    // 有子节点，不是终结符
    if (num > 0){
        temp = childList[0];
        setChildTag(temp);
        father -> child[0] = temp;
        father -> line = temp -> line;
        for (int i=1; i<num; i++){
            temp = childList[i];
            setChildTag(temp);
            father -> child[i] = childList[i];
        }
    }
    // 终结符、空的语法单元，空单元行号为-1
    else{
        father -> line = yyline;
        //printf("the child is %d\n", father->n);
        if ((!strcmp(name, "ID"))) {
            //printf("u have enter %s into it\n", yytext);
            char *str;
            str = (char *)malloc(sizeof(char) * 40);
            strcpy(str, yytext);
            father->sval = str;
        }
        else if ((!strcmp(name, "TYPE"))) {
            //printf("u have enter %s into it\n", yytext);
            char *str;
            str = (char *)malloc(sizeof(char) * 40);
            strcpy(str, yytext);
            father->sval = str;
        }        
        else if (!strcmp(name, "INT")){
            father -> ival = atoi(yytext);
            //printf("%d\n", father -> INT);
        }
        else if (!strcmp(name, "FLOAT")){
            father -> rval = atof(yytext);
        }
    }
    return father;
}

void preOrder(treeNode t, int level){
    if (t != NULL && t -> line != -1){
        // 缩进
        for (int i=0; i<level; i++)
            printf("  ");
        printf("%s", t->name);
        if (!strcmp(t->name, "ID")){
            printf(": %s", t->sval);
        }
        else if (!strcmp(t->name, "TYPE"))
            printf(": %s", t->sval);
        else if (!strcmp(t->name, "INT")){
            printf(": %d", t->ival);
        }
        else if (!strcmp(t->name, "FLOAT"))
            printf(": %f", t->rval);
        // 非叶节点，打印行号
        else if (t->n != 0)
            printf("(%d)", t->line);
        printf("\n");
        for (int i=0; i<t->n; i++){
            preOrder(t->child[i], level+1);
        }
    }
}

void yyerror(char *error_msg){
    Fault_Exist = 1;
    fprintf(stderr, "Error type B at Line %d: %s.\n", yylineno, error_msg);
}

void Print_Tree(){
    if (!Fault_Exist)
        for (int j=0; j<nodeNum; j++){
            if (nodeIsChild[j] != 1)
                preOrder(nodeList[j], 0);
        }
}

int main(int argc, char **argv){
    if (argc < 2){
        return 1;
    }
    //for (int i=1; i<argc; i++){
    Fault_Exist = 0
    nodeNum = 0;
    memset(nodeList, 0, sizeof(treeNode)*START_LEN);
    memset(nodeIsChild, 0, sizeof(int)*START_LEN);

    FILE *f = fopen(argv[1], "r");
    if (!f){
        perror(argv[1]);
        return 1;
    }
    yyrestart(f);
    yyparse();
    fclose(f);
    //if (Fault_Exist)
        //continue;
 
    //}
}


