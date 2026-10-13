#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define ERROR -1
#define SUCCESS 1

typedef struct listnode
{
    int data;
    struct listnode *next;
} ListNode;

typedef struct list
{
    ListNode *head;
} List;

List *createList();
int addToHead(List *pList, int data);
int displayList(List *pList);
int addToTail(List *pList, int data);

int main()
{
    int check;

    // create a new list
    List *pList = createList();

    // check: was list created successfully?
    // if not, exit program, e.g., return 0;
    if (pList == NULL)
    {
        printf("Failed to create list.\n");
        return 0;
    }

    // add the item 12 to the HEAD of the list
    // add the item 3 to the HEAD of the list
    // add the item 78 to the HEAD of the list
    addToHead(pList, 12);
    addToHead(pList, 3);
    addToHead(pList, 78);

    // display the list - it should show 78, 3, 12
    displayList(pList);

    // add the item 55 to the TAIL of the list
    // add the item 4 to the TAIL of the list
    addToTail(pList, 55);
    addToTail(pList, 4);

    // display the list
    displayList(pList);

    return 0;
}

List *createList()
{
    // allocate memory for a new list using malloc
    List *pList = (List*)malloc(sizeof(List));

    // check: did it work?
    // if it didn't work, what value to return from this function?
    if (pList == NULL)
    {
        return NULL;
    }

    // initialise field(s) in List
    pList->head = NULL;

    // return (pointer to) newly allocated List
    return pList;
}

int addToHead(List *pList, int data)
{
    // validate inputs?
    if (pList == NULL)
    {
        return ERROR;
    }

    // if (data == NULL)
    // {
    //     return ERROR;
    // }

    // allocate memory for a new list node
    ListNode* pNode = (ListNode*)malloc(sizeof(ListNode));

    // check: what if memory allocation fails?
    // return an appropriate result
    if (pNode == NULL)
    {
        return ERROR;
    }

    // initialise fields in new list node
    pNode->data = data;
    pNode->next = NULL;

    // get ready to add new node to list
    // check: is list empty? what to do?
    if (pList->head == NULL)
    {
        pList->head = pNode;
    }

    // if list not empty, add new item at head:
    // new node's next should point to current head of list
    // list's head should now point to new node
    else
    {
        pNode->next = pList->head;
        pList->head = pNode;
    }

    // return error/success code
    return SUCCESS;

}

int displayList(List *pList)
{
    // validate inputs?
    if (pList == NULL)
    {
        printf("Invalid list.\n");
        return ERROR;
    }

    // check: is list empty? what to do?
    if (pList->head == NULL)
    {
        printf("List is empty.\n");
        return ERROR;
    }

    // start at the head of the list, e.g., 'current' set to list's head
    ListNode* current = pList->head;

    // while current valid, still pointing to something
    // display current node in list
    // shift current along the list, to its 'next' node along
    while (current != NULL)
    {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");

    // return error / success
    return SUCCESS;
}

int addToTail(List *pList, int data)
{
    // validate inputs?
    if (pList == NULL)
    {
        return ERROR;
    }

    // if (data == NULL)
    // {
    //     return ERROR;
    // }

    // allocate memory for a new list node
    ListNode* pNode = (ListNode*)malloc(sizeof(ListNode));

    // check: what if memory allocation fails?
    // return an appropriate result
    if (pNode == NULL)
    {
        return ERROR;
    }

    // initialise fields in new list node
    pNode->data = data;
    pNode->next = NULL;

    // get ready to add new node to list
    // check: is list empty? what to do?
    if (pList == NULL)
    {
        return ERROR;
    }

    if (pList->head == NULL)
    {
        pList->head = pNode;
        return SUCCESS;
    }

    // if list not empty, we need to find where the
    // current tail node is. If we go through the list
    // from the beginning an attempt to find the last node
    // there - the last node will have a 'next' field of NULL
    // (i.e., nothing after it). This is were you need to add
    // the node - after this last one.
    ListNode* current = pList->head;
    while (current->next != NULL)
    {
        current = current->next;
    }
    current->next = pNode;

    // return error/success code
    return SUCCESS;
}