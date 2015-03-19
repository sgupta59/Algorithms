package divideandconquer;


import java.util.Stack;

public class MaxRectangleAreaInHistrogram {
    public static int MaxRectangleArea(int[] A) {
        Stack<Integer> s = new Stack<Integer>();
        if(A == null || A.length == 0)
            return 0;
        // Initialize max area
        int maxArea = 0;
        int i = 0;
        // run through all bars of given histogram
        while(i < A.length) {
            // If current bar is higher than the bar of the stack peek, push it to stack. 
            if(s.empty() || A[s.peek()] <= A[i])
                s.push(i++);
            else {
                // if current bar is lower than the stack peek, calculate area of rectangle with stack top as the smallest bar.
                // 'i' is 'right index' for the top and element before top in stack is 'left index'
                int top = s.pop();
                // calculate the area with A[top] stack as smallest bar and update maxArea if needed
                maxArea = Math.max(maxArea, A[top] * (s.empty() ? i : i - s.peek() - 1));
            }
        }
        // Now pop the remaining bars from stack and calculate area with every popped bar as the smallest bar.
        while(!s.isEmpty()) {
            int top = s.pop();
            maxArea = Math.max(maxArea, A[top] * (s.empty() ? i : i - s.peek() - 1));
        }
        return maxArea;
    }
    static int largestArea(int arr[], int len)
    {
    	int[] area = new int[len];
    	int n, i, t;
    	Stack<Integer> St = new Stack<Integer>();  //include stack for using this #include<stack>
    	boolean done;

    	for (i=0; i<len; i++)
    	{
    		while (!St.empty())
    		{
    			if(arr[i] <= arr[St.peek()])
    			{
    				St.pop();
    			}
    			else
    				break;
    		}
    		if(St.empty())
    			t = -1;
    		else
    			t = St.peek();
    		//Calculating Li
    		area[i] = i - t - 1;
    		St.push(i);
    	}

    	//clearing stack for finding Ri
    	while (!St.empty())
    		St.pop();

    	for (i=len-1; i>=0; i--)
    	{
    		while (!St.empty())
    		{
    			if(arr[i] <= arr[St.peek()])
    			{
    				St.pop();
    			}
    			else
    				break;
    		}
    		if(St.empty())
    			t = len;
    		else
    			t = St.peek();
    		//calculating Ri, after this step area[i] = Li + Ri
    		area[i] += t - i -1;
    		St.push(i);
    	}

    	int max = 0;
    	//Calculating Area[i] and find max Area
    	for (i=0; i<len; i++)
    	{
    		area[i] = arr[i] * (area[i] + 1);
    		if (area[i] > max)
    			max = area[i];
    	}

    	return max;
    }
    public static void main(String[] args)
    {
    	int[] hist1 = {6, 2, 5, 4, 5, 2, 6};
    	int[] hist = { 2,3,5,5};
    	int area = MaxRectangleArea(hist);
    	int area1 = largestArea(hist,hist.length);
    	System.out.println("abc");
    }
}