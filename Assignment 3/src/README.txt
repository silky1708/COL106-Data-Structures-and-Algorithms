README

Space complexity comparison::
In double hashing obviously the space used is more in order to make double hashing efficient and most of the space won't
be used since the maximum size of dataset is approx. 2/3rd the size of table.
However in separate chaining approach we can have size of hashtable as less as 1. But in that case the time complexity
suffers and all operations would become O(height of bst), whereas in double hashing with some extra space it's guaranteed
to have an average time complexity of O(1).

TIME COMPLEXITY COMPARISON
Separate chaining approach:--
1. insert method
    theoretically the time complexity should be O(logn) where n is the number of nodes already present in the BST at that index.
    My implementation follows the theoretical time complexity i.e.,O(logn).
    First I check whether the key is already present or not: if it is already present then I simply return -1.
    cost of this operation is the same as the cost of contains method i.e., O(logn).
    I find the index and then traverse through the bst at that index until I reach a leaf node then accordingly I place
    the new node as leaf's next.
    cost of traversal O(logn) or O(height of bst)
    therefore total cost of insertion becomes O(logn).

    Worst case complexity:
    In the worst bst may behave as a singly linked list; then the insertion would take O(n); n= number of nodes.
    It happens when all nodes are either to left or to the right of root node.

2. update method
    theoretically it would take same time as locating that node in the tree,i.e.,O(logn).
    My implementation first checks if the key is already present in the node and if it is then I locate it and update it.
    If it is not found then simply return negative value.
    cost of operation in either case is therefore = O(logn).

    Worst case complexity = O(n) if bst is more like linked list and the node is a leaf node of bst.

3. delete method
    theoretically it should take O(logn) which includes traversing(O(logn)) and some extra work(O(1)) for deleting that node.
    In my implementation I first check if it is present in the bst : if it is not then I simply return a negative value.
    If it is present then I traverse through the bst until I find that node.There are many cases after this step:
    case a. the root is a leaf node and not a root node.
    case b. the root is a leaf node and also a root node.
    case c. the root's left is null, right is not null and this node is not a root node.
    case d. the root's left is null right is not null and this node is a root node
    case e. the root's right is null and left is not null and node is not a root node.
    case f. the root's right is null and left is not null and this node is a root node
    case g. the root has non-null left and right and this node is not a root node.
    case h. the root has non-null left and right and this node is a root node.
     in cases g and h we need to find the successor of node to be deleted and replace it by its successor. So for this we find
     the smallest element in its right subtree. So now the cost of operation becomes O(logn); in other cases c,d,e,f the cost would be
     slightly lesser.

     overall time complexity becomes O(logn).

     Worst case complexity =O(n); in case of linked-list like structure and the node to be deleted is a leaf node.

4. get method
    theoretically, time complexity = O(logn)
    which holds true for my implementation as the cost of operation is same as locating that node(traversing) and then
    simply returning the student object stored in that slot.

    worst case time complexity = O(n) if bst is more like a linked list and node is a leaf node.

5. contains method
    again same as all other operations in the way that we traverse until we find the node or a leaf node is reached;
    in average case it would be O(logn); whereas in the worst case it would behave as a linked list and iteration through
    all the nodes will be required making contains O(number of nodes).
    average time complexity = O(logn)
    worst case time complexity = O(n)


6. address method
        same complexity as locating that node;either it is present or not;if not return -1.
        we traverse through all the nodes stored in bst and also keep updating a string with L or R depending on
        where we move next in the bst.Return this string when the node is found.
       time complexity = O(logn)
       worst case time complexity = O(logn)


DOUBLE HASHING APPROACH
NOTE::
1. The size of hashtable is prime.
2. The size of hashtable lies in between 1.5N and 2N where N is the maximum size of dataset.
3. The hash functions that we are using are fast but not necessarily uniform.
//
1. insert method
    theoretically should be O(1).
    average time complexity = O(1)
    Whenever there is a collision a new hash function is generated; in the best case we'll consider that this new index
    is not already occupied and in the worst case we consider it is occupied already.
    In my implementation I first check if the value at index is null or not. If it's null, just insert there else keep
    checking until an empty slot is found and it is guaranteed that an empty slot will be found as the size of hashtable
    is comparatively large.

    Worst case time complexity =O(n)
    It occurs when the hash function generates indices in such a way that all possible slots are occupied so we have to
    traverse through the entire filled portion of the table which is O(objects currently present in hashtable).



2. update method
    theoretical time complexity = O(1)
    average time complexity for this code = O(1)
    worst time complexity = O(size of hash table)

    Inside update method index is generated until the key is found or we get the same first index which we got with hash
    function h1(i.e., we have iterated through the entire array and key was not found; return -1).
    so the average time complexity would be O(1) and worst time complexity would be O(size of hash table).

3. delete method
    theoretical time complexity = O(1)
    average time complexity for this code = O(1)
    worst case time complexity = O(size of hashtable) when the object is not present in the hashtable else it would be
    O(number of student objects stored) if element is already present.

    Iterate through all objects is required until either we get that node or the hashtable has been iterated completely.


4. get method
    theoretical time complexity = O(1)
    average time complexity =O(1)
    Inside this method index is generated corresponding to the given key.If it's null generate a new index else check if
    concat of first name and last name matches with the same stored in hashtable. If it matches return the student object
    else continue iteration on the hash table.

 ::  worst case time complexity :-
    O(size of table)
    When the hash function generates indices which are already occupied everytime unless all elements have been
    iterated


5. contains method
    theoretical time complexity = O(1)
    average time complexity for my implementation = O(1)

    Inside contains method check whether the slot is empty or not.If it's empty continue iteration else check if the
    key matches or not. If it matches return true else continue iteration.
    Exit condition:- the next index that comes is the very first index calculated using h1 on the same key.
    worst case time complexity = O(size of hashtable) when key is not present

6. address method
    theoretically time required should be same as the time to locate that object i.e. O(1)
    average time complexity = O(1)
    If the hashtable contains key search for it and return its index else print E
    worst case complexity = O(size of hashtable) = O(N)


Some interesting findings:--
1. IN SCBST: Instead of removing or updating a node by a new node we can simply change the data inside the node while still maintaining
    the left and right references. This way we can save some space.
2. The nodes with same key i.e., same first name have to be placed in the right subtree to maintain the binary search tree
    in certain exceptional cases.
3. Since the size of hashtable in case of double hashing approach is prime and size of hashtable is greater than 1.5 times
    the maximum size of dataset there is really very low probability that two consecutive indices are same and also the
    complete hashtable is iterated before we repeat any of the indices.




