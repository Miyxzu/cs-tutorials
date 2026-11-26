 /*
 * SAMPLE / STARTER CODE FOR WEEK 3 - STACK DATA STRUCTURE 
 *
 * STACK IMPLEMENTATION
 *
 */

/* include the header file that contains the definition of the 
 * stack data structures and function declarations */
#include "stack.h"

/* include the C header file stdlib.h for access to 'malloc' */
#include <stdlib.h>

/*
 * ############## 
 * FUNCTION DEFINITIONS / IMPLEMENTATIONS
 *
 * The following are the implementations of the functions for
 * the Stack data structure. These functions are declared in
 * the corresponding header file (stack.h) along with the 
 * stack data structures that will be used.
 * 
 */

/**
 * A function to create a new Stack. It should allocate 
 * memory for an empty Stack, initialise the fields
 * to a valid, default value, and return a pointer to 
 * allocated Stack (or NULL, in the event of an error)
 */
Stack *createStack()
{
	// declare a temporary, local variable to store
	// a pointer to a stack
	Stack *pStack = NULL;

	// allocate memory for a new Stack data structure
	// using malloc, and assign the pointer to the  
	// allocated memory (returned by malloc) into the 
	// pStack variable
	pStack = (Stack*)malloc(sizeof(Stack));

	// check for memory allocation failure...

	// if the value returned by malloc is 'NULL'
	if (pStack == NULL)
	{
		// This signifies a memory allocation failure.
		// An error code should be returned to reflect this.
		// Usually, the error code MEMORY_ALLOCATION_ERROR
		// should be returned (e.g. return MEMORY_ALLOCATION_ERROR;)
		// However, this function expects either a valid pointer
		// to a Stack to be returned OR a value of NULL in the event
		// of failure to create the Stack. So, we will return 
		// a value of NULL on this occasion.
		return NULL;
	}

	// if we get here, there hasn't been a memory allocation
	// failure and we so we can proceed to initialise and
	// return the newly created Stack...

	// initialise the fields in the Stack
	// to default values - reflecting an
	// empty stack
	pStack->top = NULL;

	// return the pointer to the newly
	// created Stack
	return pStack;
}

/**
 * A function to "push" a new node onto the top
 * of the Stack. A new Node should be allocated
 * and its fields initialised to values given 
 * and/or default initial values. The new node 
 * should then be added to the top of the Stack.
 *
 * @param pStack A pointer to the Stack
 * @param score The data (a score) being added to the Stack
 * 
 * @returns A code that signifies SUCCESS or an error
 * 
 */
int push(Stack *pStack, double score)
{
	// first, check that we have a pointer to a valid Stack...

	// if the stack pointer isn't valid (it has to be non-null)
	if (pStack == NULL)
	{
		// return error code to signify invalid input parameter
		return INVALID_INPUT_PARAMETER;
	}

	// else, ok to proceed...

	// check that we have received a valid 'score' - it is
	// expected to be a value between 0 and 100
	if ((score < 0) || (score > 100))
	{
		// return error code to signify invalid input parameter
		return INVALID_INPUT_PARAMETER;
	}

	// else, ok to proceed...

	// attempt to allocate memory for a new
	// new node to go onto the stack
	Node *newNode = (Node*)malloc(sizeof(Node));

	// if failed to allocate memory for new node
	if (newNode == NULL)
	{
		// return error code that signifies a memory allocation error
		return MEMORY_ALLOCATION_ERROR;
	}

	// else, ok to proceed....

	// Initialise the fields in the new node.
	// In this case, recording the 'score'
	// provided as an input to this function 
	newNode->score = score;

	// NOTE: usually, we would want to make sure that 
	// the 'next' field in the Node is initialised to NULL. 
	// We would do this with any other pointer fields in 
	// the node too if it had them. This will help to ensure the
	// validity of the Stack and prevent pointers from
	// referring to random locations in memory. So, we would
	// do the following:
	newNode->next = NULL;

	// However, we are immediately going to set the 'next'
	// field to a different value on this occasion and so
	// the necessity of the above step is lessened but probably
	// still just a good thing to get into habit of doing.

	// set the new node's 'next' pointer to point to
	// whatever is currently at the top of the stack
	// (because the new node will become the new top
	// of the stack and the existing top will shift
	// down below it)
	newNode->next = pStack->top;

	// make the new node the new top of the stack
	pStack->top = newNode;

	// return success code 
	return SUCCESS;
}

/**
 * A function to "pop" or remove a node from the
 * top of the Stack. The data for the node being 
 * removed should be copied into the given variable
 * (pScore) so that it can be retained after the
 * function is called.
 *
 * @param pStack A pointer to the Stack
 * @param pScore A pointer to a variable into which
 *               the data being removed from the
 *               Stack can be copied.
 * 
 * @returns A code that signifies SUCCESS or an error
 * 
 */
int pop(Stack *pStack, double *pScore)
{
	// if we haven't received a valid pointer to a stack
	if (pStack == NULL)
		return INVALID_INPUT_PARAMETER;

	// otherwise, we can proceed..

	// if we haven't received a valid pointer to a
	// variable (pScore) in which to store the value
	// being popped from the stack
	if (pScore == NULL)
		return INVALID_INPUT_PARAMETER;

	// otherwise, we can proceed..

	// if the Stack is empty, there is nothing to pop and
	// this would be an invalid operation
	if (pStack->top == NULL)
		return INVALID_STACK_OPERATION;

	// otherwise, we can proceed..

	// copy the value from the node being popped
	// from the stack into the variable provided
	// NOTE: you can see that we use the de-referencing
	// operator '*' here: *pStack. This tells our
	// program to go to the memory location that pStack
	// refers to and place the 'score' value in there
	*pScore = pStack->top->score;

	// having obtained a copy of the data in the node
	// that we are about to remove / pop off the stack,
	// we can go ahead and remove the Node itself....

	// create a temporary pointer to store a reference
	// to the node currently at the top of the stack
	// (this is the node we will be removing / popping)
	Node *nodeToRemove = pStack->top;

	// modify the top of the stack to now be the
	// next node down (this will effectively bypass
	// and remove the existing top node from the 
	// stack but we still have a reference to it in 
	// the variable 'nodeToRemove' as per above, so 
	// we haven't fully lost it yet)
	pStack->top = pStack->top->next;

	// remove / free the memory for the node that has
	// now been removed from the stack
	nodeToRemove->next = NULL; // not really necessary
	nodeToRemove->score = 0; // not really necessary
	free(nodeToRemove); // extremely necessary!

	// return success code
	return SUCCESS;
}

/**
 * A function to "peek" at or copy the data that is
 * held in the top Node of the Stack BUT without
 * removing the node from the Stack. The data should 
 * be copied into the given variable (pScore).
 *
 * @param pStack A pointer to the Stack
 * @param pScore A pointer to a variable into which
 *               the data being "peeked" at on the
 *               Stack can be copied.
 * 
 * @returns A code that signifies SUCCESS or an error
 * 
 */
int peek(Stack *pStack, double *pScore)
{
	// if we haven't received a valid pointer to a stack
	if (pStack == NULL)
		return INVALID_INPUT_PARAMETER;

	// otherwise, we can proceed..

	// if we haven't received a valid pointer to a
	// variable (pScore) in which to store the value
	// being peeked at on the stack
	if (pScore == NULL)
		return INVALID_INPUT_PARAMETER;

	// otherwise, we can proceed..

	// if the Stack is empty, there is nothing to peek
	// at and this would be an invalid operation
	if (pStack->top == NULL)
		return INVALID_STACK_OPERATION;

	// otherwise, we can proceed..

	// copy the value from the node being peeked at
	// from the stack into the variable provided
	*pScore = pStack->top->score;

	// return success code
	return SUCCESS;
}

/**
 * A function to free all memory used by the
 * Stack. This function can be called when the Stack
 * is either empty or non-empty. 
 *
 * @param pStack A pointer to the Stack
 * 
 * @returns A code that signifies SUCCESS or an error
 * 
 */
int freeStack(Stack *pStack)
{	
	// if we haven't received a valid pointer to a stack
	if (pStack == NULL)
		return INVALID_INPUT_PARAMETER;

	// otherwise, we can proceed..

	// while the stack isn't empty (i.e. contains at
	// least one node at the top)
	while (pStack->top != NULL)
	{
		// get a temporary pointer to the node
		// at the top of the stack
		Node *temp = pStack->top;

		// shift the top of the stack to the next
		// node down after the current top
		pStack->top = pStack->top->next;

		// free the memory for the node that
		// was previously at the top (and still
		// referred to by 'temp')
		free(temp);
	}

	// all nodes in the stack have been freed.
	// now free the memory for the stack itself
	free(pStack);

	// return success code
	return SUCCESS;
}
