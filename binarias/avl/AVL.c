#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//-------------------------------------------------- BINARY TREE AVL ---------------------------------------------------------
typedef struct Node
{
    int height;
    struct Node *left, *right;
    int element;
} Node;

Node *createNode(int x)
{
    Node *node = malloc(sizeof(Node));
    node->left = node->right = NULL;
    node->height = 0;
    node->element = x;
    return node;
}

//-------------------------------------------------- BALANCE ---------------------------------------------------------

//------------------------------- NODE LEVEL ------------------------------
int max(int a, int b){
    return (a > b)? a : b;
}

int nodeHeight(Node* no) {
    if(no == NULL) {
        return -1;
    }else {
        return no->height;
    }
}

int factorBalance(Node* no) {
    if(no){
        return (nodeHeight(no->right) - nodeHeight(no->left));
    }else {
        return 0;
    }
}

//------------------------------- LEFT ROTATE -----------------------------
Node *leftRotate(Node *no)
{
    Node *right = no->right;
    Node *rightLeft = right->left;

    right->left = no;
    no->right = rightLeft;

    no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
    right->height = max(nodeHeight(right->left), nodeHeight(right->right))+1;

    return right;
}

//------------------------------- RIGHT ROTATE -----------------------------
Node *rightRotate(Node *no)
{
    Node *left = no->left;
    Node *leftRight = left->right;

    left->right = no;
    no->left = leftRight;

    no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
    left->height = max(nodeHeight(left->left), nodeHeight(left->right))+1;

    return left;
}
//------------------------- LEFT RIGHT ROTATE -------------------------------
Node *leftRightRotate(Node *no)
{
    no->left = leftRotate(no->left);
    return rightRotate(no);
}

//------------------------- RIGHT LEFT ROTATE -------------------------------
Node *rightLeftRotate(Node *no)
{
    no->right = rightRotate(no->right);
    return leftRotate(no);
}

Node *balance(Node *no)
{
    if (no != NULL)
    {
        int fb = factorBalance(no);

        if (fb > -2 && fb < 2)
        { // is already balance
            no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
        }
        else if (fb == 2) // is unbalanced to the right
        {
            int childFb = factorBalance(no->right);

            if (childFb <= -1) // the child is unbalanced to the left
            {
                no->right = rightRotate(no->right);
            }
            no = leftRotate(no);
        }
        else // is unbalanced to the left
        {
            int childFb = factorBalance(no->left);

            if (childFb >= 1)
            {
                no->left = leftRotate(no->left);
            }
            no = rightRotate(no);
        }
    }

    return no;
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------

//------------------------------------------------------------ INSERT ------------------------------------------------------------
Node *insert(int x, Node *i)
{
    if (i == NULL)
    {
        return createNode(x);
    }
    else{
        if(x < i->element) {
            i->left = insert(x, i->left);
        }else {
            i->right = insert(x, i->right);
        }
    }
    i->height = max(nodeHeight(i->left), nodeHeight(i->right))+1;

    i = balance(i);

    return i;
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------

//------------------------------------------------------------ TRAVERSAL ------------------------------------------------------------

//------------------------------- IN ORDER ------------------------------
void inOrder(Node *i)
{
    if (i != NULL)
    {
        inOrder(i->left);
        printf("%d (FB: %d); ", i->element, factorBalance(i));
        inOrder(i->right);
    }
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------

//----------------------------------------------------------------------- MAIN --------------------------------------------------------
int main()
{

    int arr[] = {1, 2, 3, 4, 5, 6, 7,8, 9, 10, 12, 132, 0};
    int total = sizeof(arr) / sizeof(arr[0]);

    Node *root = NULL;

    for (int i = 0; i < total; i++)
    {
        root = insert(arr[i], root);
    }
    printf("\n");
    inOrder(root);
    printf("\n");
    return 0;
}