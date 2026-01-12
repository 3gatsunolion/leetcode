class MinStack {
    private:
        stack<int> minStack;
        stack<int> nums;
    public:
        MinStack() {
            
        }
        
        void push(int val) {
            int currMin = val;
            if (!minStack.empty()) {
                currMin = min(currMin, minStack.top());
            }
            minStack.push(currMin);
            nums.push(val);
        }
        
        void pop() {
            minStack.pop();
            nums.pop();
        }
        
        int top() {
            return nums.top();
        }
        
        int getMin() {
            return minStack.top();
        }
    };
    
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack* obj = new MinStack();
     * obj->push(val);
     * obj->pop();
     * int param_3 = obj->top();
     * int param_4 = obj->getMin();
     */