#include <iostream>
#include <stack>
using namespace std;


struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

bool compareTwo(TreeNode* leftSub, TreeNode* rightSub) {
    if (!leftSub && !rightSub) return true;
    if (!leftSub || !rightSub) return false;
    return  leftSub->val == rightSub->val
            && compareTwo(leftSub->left, rightSub->right)
            && compareTwo(leftSub->right, rightSub->left);
}

bool isSymmetric(TreeNode* root) {
    // ITERATIVE SOLN
    // // stack for left subtree
    // // stack for right subtree
    // stack<TreeNode*> leftSubtrees;
    // stack<TreeNode*> rightSubtrees;
    // leftSubtrees.push(root->left);
    // rightSubtrees.push(root->right);
    // // keep looping till both the sizes are different or zero
    // while(leftSubtrees.size() != 0 && leftSubtrees.size() == rightSubtrees.size()) {
    //     TreeNode *leftSub = leftSubtrees.top(); leftSubtrees.pop();
    //     TreeNode *rightSub = rightSubtrees.top(); rightSubtrees.pop();
    //     if (!leftSub && rightSub
    //         || !rightSub && leftSub
    //         || leftSub->val != rightSub->val)
    //         return false;
    //     // next comparison 1
    //     rightSubtrees.push(leftSub->right);
    //     leftSubtrees.push(rightSub->left);
    //     // next comparison 2
    //     leftSubtrees.push(leftSub->left);
    //     rightSubtrees.push(rightSub->right);
    // }
    // return true;
    
    // RECURSIVE SOLN
    return compareTwo(root, root); 
}

TreeNode* invertTree(TreeNode* root) {
    if (root == NULL)
        return NULL;
    TreeNode* temp = root->left;
    root->left = invertTree(root->right);
    root->right = invertTree(temp);
    return root; 
}

int main(int argc, char const *argv[])
{
    stack<TreeNode*> leftSubtrees;
    leftSubtrees.push(NULL);
    leftSubtrees.push(NULL);
    cout << leftSubtrees.size() << endl;
    return 0;
}
