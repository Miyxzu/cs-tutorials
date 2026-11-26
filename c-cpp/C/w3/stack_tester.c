/*
 * SAMPLE / STARTER CODE FOR WEEK 3 - STACK DATA STRUCTURE 
 *
 * STACK TESTER PROGRAM
 *
 */

/** include stdio for access to printf, scanf, etc. */
#include <stdio.h>

/** include 'stack.h' for the Stack data structures and functions */
#include "stack.h"

/**
 * Main function to run a series of tests on the Stack data structure
 *
 */
int main() 
{	
	printf("STACK TESTER PROGRAM...\n");

	// ##TEST: create an empty Stack
	// a variable to store a reference / pointer to 
	// a newly created stack
	Stack *pStack = NULL;

	// call function to create a new stack
	pStack = createStack();

	// if the stack wasn't created successfully,
	// report an error and exit the program (no
	// point in going any further)
	if (pStack == NULL)
	{
		printf("ERROR: failed to create empty Stack\n");
		return 0;
	}
	else
	{
		printf("STACK CREATED!...\n");
	}

	// otherwise, we now have an empty Stack.
	// Check that the stack has been initialised correctly,
	// E.g. 'top' should be NULL to signify an empty Stack
	if (pStack->top != NULL)
	{
		printf("ERROR: empty Stack not correctly initialised. Doing so now.\n");
		pStack->top = NULL;
	}

	// ##TEST: try to push something onto a non-existant Stack

	// a test value to push onto the Stack
	double testScore = 34.5;

	// a variable to store the result of calling the Stack function
	int result = 0;

	// call the stack 'push' function but deliberately providing it
	// with an invalid Stack pointer (a NULL pointer). This will 
	// check to see if the 'push' function is validating its inputs
	// correctly. An error or success code will be returned.
	result = push(NULL, testScore); 

	// the error code 'INVALID_INPUT_PARAMETER' should have been returned
	if (result == INVALID_INPUT_PARAMETER)
	{
		printf("TEST PASSED: check for NULL Stack pointer in 'push'\n");
	}
	else
	{
		printf("TEST FAILED: check for NULL Stack pointer in 'push'\n");
	}


	// ## TEST: try pushing invalid (out of range) data onto a valid stack
	// e.g., game score should be in the range 0 to 100, try entering a value
	// of 150 instead
	result = push(pStack, 150); 

	// the error code 'INVALID_INPUT_PARAMETER' should have been returned
	if (result == INVALID_INPUT_PARAMETER)
	{
		printf("TEST PASSED: check for out of range 'score' in 'push'\n");
	}
	else
	{
		printf("TEST FAILED: check for out of range 'score' in 'push'\n");
	}


	// ## TEST: add a valid score to a valid Stack	
	result = push(pStack, testScore); 

	// the success code 'SUCCESS' should have been returned
	if (result == SUCCESS)
	{
		printf("TEST PASSED: add valid score to valid stack in 'push'\n");
	}
	else
	{
		printf("TEST FAILED: add valid score to valid stack in 'push'\n");
	}


	// Right now, the top of the stack should be pointing to newly added node, that
	// node should contain the score 'testScore', and there should be no
	// 'next' node after it in the stack. Check this:
	if ((pStack->top != NULL) && (pStack->top->score == testScore) && (pStack->top->next == NULL))
	{
		printf("The stack is currently valid\n");

	}
	else
	{
		printf("Non valid stack. Check\n");
	}


	// ## TEST: add another valid score to a valid Stack	
	double testScore2 = 67.8;
	result = push(pStack, testScore2); 

	// the success code 'SUCCESS' should have been returned
	if (result == SUCCESS)
	{
		printf("TEST PASSED: add another valid score to valid stack in 'push'\n");
	}
	else
	{
		printf("TEST FAILED: add another valid score to valid stack in 'push'\n");
	}

	// Right now, the stack should be like this:
	// pStack -> node with testScore2 -> node with testScore -> null
	// check this is correct
	if ((pStack->top != NULL) && (pStack->top->score == testScore2) && (pStack->top->next != NULL) && (pStack->top->next->score == testScore) && (pStack->top->next->next == NULL))
	{
		printf("The stack is currently valid\n");

	}
	else
	{
		printf("Non valid stack. Check\n");
	}


	// ##TEST: try to pop something from the Stack...

	// a variable which will be used to receive the data which is 
	// popped from the stack
	double poppedScore = 0;

	// call the pop function on a valid Stack
	// we provide the memory address of the variable
	// that will receive the popped value (&poppedScore)
	result = pop(pStack, &poppedScore);

	// the success code 'SUCCESS' should have been returned
	if (result == SUCCESS)
	{
		printf("TEST PASSED: pop item from Stack\n");
	}
	else
	{
		printf("TEST FAILED: pop item from Stack\n");
	}

	// check the value popped is correct
	if (poppedScore == testScore2)
	{
		printf("TEST PASSED: value popped from Stack is correct\n");
	}
	else
	{
		printf("TEST FAILED: value popped from Stack is correct\n");
	}

	// Right now, the stack should be like this:
	// pStack -> node with testScore -> null
	// check this is correct
	if ((pStack->top != NULL) && (pStack->top->score == testScore) && (pStack->top->next == NULL))
	{
		printf("The stack is currently valid\n");

	}
	else
	{
		printf("Non valid stack. Check\n");
	}

    // when finished, remember to free the Stack from memory
    if (freeStack(pStack) == SUCCESS)
    {
    	printf("Stack now freed.\n");
    }
    else
    {
    	printf("Error freeing stack.\n");
    }

    // end of main
	return 0;
}
