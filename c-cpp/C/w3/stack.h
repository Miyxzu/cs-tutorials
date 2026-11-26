/*
 * SAMPLE / STARTER CODE FOR WEEK 3 - STACK DATA STRUCTURE 
 *
 * STACK HEADER FILE
 *
 */

/* 
 * ############
 * ERROR CODES
 * The following error and success codes are defined so that they can be
 * used within the Stack-related functions that you will implement.
 * These must be used as return values from functions where applicable. 
 * Here's an example of how you would return an error code from a
 * function for example:
 *
 * return INVALID_INPUT_PARAMETER;
 *
 * The numerical value of the constant will be returned from your function
 * and this can be checked in other parts of your code as follows:
 *
 * int result = pushOntoStack(stack, value);
 * if (result == INVALID_INPUT_PARAMETER)
 * {
 *    // report error
 * }
 *
 */
#define SUCCESS                  100   // Value to be returned if a function is completed successfully
#define INVALID_INPUT_PARAMETER  101   // Value to be returned if you detect that one of the input parameters 
                                       // into your function is invalid, e.g. it is NULL or an unexpected value
#define MEMORY_ALLOCATION_ERROR  102   // Value to be returned if a memory allocation error occurs during your function
#define INVALID_STACK_OPERATION  103   // Value to be returned if your function detects an operation that is being
                                       // attempted on your stack data structure that isn't valid or your stack
                                       // isn't in a ready state to receive. For example, an attempt is made to remove
                                       // an item from an empty stack or to peek at an item on an empty stack
#define NOT_IMPLEMENTED          104   // Value to be returned if you haven't implemented this function yet

/*
 * ############## 
 * DATA STRUCTURES
 *
 * The following data structures are defined for you to
 * use in your Stack program. 
 *
 */

/*
 * NODE
 *
 * A struct to represent one of the nodes or items
 * that are being stored on the Stack. Contains data
 * (in the form of 'score') and a reference to the
 * next node down on the Stack after it.
 *
 */
typedef struct node {
	double score;
	struct node *next;
} Node;

/*
 * STACK
 *
 * A struct to represent the Stack itself. Contains a
 * reference to the Node at the top of the Stack which
 * then serves as an entry point for subsequent nodes
 * below.
 *
 */
typedef struct stack {
	Node *top;
} Stack;

/*
 * ############## 
 * FUNCTION DECLARATIONS
 *
 * The following are declarations of the functions which
 * will be used to implement the Stack functionality. The
 * implementation of these functions will be provided within
 * a corresponding code file, 'stack.c'. 
 *
 */

/**
 * A function to create a new Stack. It should allocate 
 * memory for an empty Stack, initialise the fields
 * to a valid, default value, and return a pointer to 
 * allocated Stack (or NULL, in the event of an error)
 */
Stack *createStack();

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
int push(Stack *pStack, double score);

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
int pop(Stack *pStack, double *pScore);

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
int peek(Stack *pStack, double *pScore);

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
int freeStack(Stack *pStack);
