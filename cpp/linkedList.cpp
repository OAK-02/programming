#include <iostream>
#include <vector>
#include <algorithm>        // for find
#include <stack>
using namespace std;

class ListNode {
public:
    int data;
    ListNode *next;
    
    ListNode(int data) {
        this->data = data;
        this->next = NULL;
    }

    ListNode() {
        this->next = NULL;
    }
};

void printList(ListNode *n) {
    while (n != NULL) {
        cout << n->data << " ";
        n = n->next;
    }
    cout<<endl;
}

ListNode *createLL() {
    ListNode *head = new ListNode(0);
    ListNode *temp = head;
    temp->next = new ListNode(16);
    temp = temp->next;
    temp->next = new ListNode(5);
    // temp = temp->next;
    // temp->next = new ListNode(-1);
    // temp = temp->next;
    // temp->next = new ListNode(-2);
    // temp = temp->next;
    // temp->next = new ListNode(-2);
    // temp = temp->next;
    return head;
}

ListNode *createListNode(int data) {
    ListNode *newListNode = new ListNode();
    newListNode->data = data;
    newListNode->next = NULL;
    return newListNode; 
}

// Insert at position
ListNode *insertAtTail(ListNode *head, int data, int position) {
    ListNode *newListNode = new ListNode(data);
    if (!position) {
        newListNode->next = head;
        return newListNode;
    }
    ListNode *trav = head;
    for(int i = 0; i < position-1; ++i) {
        trav = trav->next;
    }
    newListNode->next = trav->next;
    trav->next = newListNode;
    return head;
}

ListNode* middleListNode(ListNode* head) {
    ListNode* slow, fast;
    std::vector<ListNode*> li;
    for (ListNode* temp = head; temp != NULL; temp = temp->next)
        li.push_back(temp);

    return li.at(li.size()/2);
}

ListNode* reverseLL(ListNode* head) {
    if (head == NULL || head->next == NULL)
            return head;
    ListNode* behind = head;
    ListNode* ahead = head->next;
    behind->next = NULL;
    while (ahead != NULL) {
        ListNode *temp = ahead->next;
        ahead->next = behind;
        behind = ahead;
        ahead = temp;
    }
    return behind;
}

ListNode* removeElements(ListNode* head, int val) {
    if (head == NULL)
        return NULL;
    else if (head->data == val) {
        head = removeElements(head->next, val);
    } else {
        head->next = removeElements(head->next, val);
    }
    return head;
}

ListNode* deleteDuplicates(ListNode* head) {
    if (head == NULL)
        return head;

    vector<int> valsSoFar;
    valsSoFar.push_back(head->data);
    ListNode* temp = head;
    while (temp->next != NULL) {
        int data = temp->next->data;
        vector<int>::iterator it = find(valsSoFar.begin(), valsSoFar.end(), data);
        if (it != valsSoFar.end()) {
            temp->next = temp->next->next;
        } else {
            valsSoFar.push_back(data);
            temp = temp->next;
        }
    }
    return head;
}

// HELPER
ListNode* findMid(ListNode *head) {
    ListNode *slow = head, *fast = head->next;
    while(fast != NULL && fast->next != NULL) {
        slow = slow->next;
        fast = fast->next->next;
    }   
    return slow;
}

int main(int argc, char *argv[]) {
    ListNode *head = createLL();
    // head = insertAtTail(head, 3, 2);
    // head = insertAtTail(head, 5, 2);
    // printList(head);
    // // head = reverseLL(head);
    // printList(head);
    // cout << middleListNode(head)->data << endl;
    // printList(head);
    // head = deleteDuplicates(head);
    // printList(head);
    ListNode *node = findMid(head);
    cout << node->data << endl;
    return 0;
}