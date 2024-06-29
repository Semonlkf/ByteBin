#ifndef _INCLUDE_LAB1_
#define _INCLUDE_LAB1_

#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

# define maxNum 10
# define START_LEN  1000
extern int yylineno;
extern char* yytext;
extern int yylex(void);

struct TREE{
    int line;
    char* name;
    int n; // num of children
    struct TREE *child[maxNum];
    union{
        char* sval;
        int ival;
        float rval;
    };
};
typedef struct TREE *treeNode;

extern int Fault_Exist;
extern int nodeNum;
extern treeNode nodeList[1000];
extern int nodeIsChild[1000];

int transfer(char * trans_p);

void setChildTag (treeNode node);

treeNode newNode(char *name, int num, int yyline, treeNode childList[]);

void preOrder(treeNode t, int level);

void Print_Tree();


void yyerror(char *msg);

#endif
