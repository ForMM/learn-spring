package com.example.demo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestMap {

    @Test
    public void TestHashMap(){
        Map<String,String> ob = new HashMap<>();
        ob.put("aa","11");
        ob.put("bb","22");
        ob.put("aa","11");
        ob.put("cc","55");

        Set<Map.Entry<String, String>> entries = ob.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey());
        }


        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        int[] ints1 = twoSum1(nums, target);
        int[] ints2 = twoSum2(nums, target);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.toString(ints2));

        ListNode node = new ListNode(0);
        node.setVal(2);
        node.setNext(new ListNode(4));
        node.getNext().setNext(new ListNode(3));

        ListNode node2 = new ListNode(0);
        node2.setVal(5);
        node2.setNext(new ListNode(6));
        node2.getNext().setNext(new ListNode(4));

        ListNode listNode = addTwoNumbers(node, node2);
        System.out.println(listNode.getVal());

        String ss = "pwwkew";
        int length = lengthOfLongestSubstring(ss);
        int i = lengthOfLongestSubstring1(ss);
        System.out.println(i);

        int[] nums1=new int[]{3};
        int[] nums2=new int[]{-2,-1};
        System.out.println(findMedianSortedArrays(nums1,nums2));

        int[][] maxta=new int[][]{{1,4,7,9},{2,3,6,8}};

        boolean numberIn2DArray = findNumberIn2DArray(maxta, 5);
        boolean numberIn2DArray1 = findNumberIn2DArray1(maxta, 6);
        System.out.println(numberIn2DArray);
        System.out.println(numberIn2DArray1);


        String lll="we are chinese";
        System.out.println(replaceSpace(lll));
        System.out.println(Arrays.toString(reversePrint(node)));

        int[] buildaa = new int[]{3,9,20,15,7};
        int[] buildbb = new int[]{9,3,15,20,7};
        TreeNode treeNode = buildTree(buildaa, buildbb);
        System.out.println(treeNode.getValue());


        System.out.println(fib(5));


    }


    /**
     *给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        List<Integer> indexArr = new ArrayList<>();
//        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if((nums[i]+nums[j])==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
//        return  indexArr.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        Map<Integer,Integer> numsMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            numsMap.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int compnent=target-nums[i];
            if (numsMap.containsKey(compnent)&&numsMap.get(compnent)!=i){
                return new int[]{i,numsMap.get(compnent)};
            }

        }
        return null;
    }


    public int[] twoSum2(int[] nums, int target) {

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if((nums[i]+nums[j])==target){
                    return new int[]{i+1,j+1};
                }
            }
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.getVal();
            int y = l2 == null ? 0 : l2.getVal();
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }


    public int lengthOfLongestSubstring(String s) {
        int aa =0;
        for (int i=0;i<s.length();i++){
            for (int j=i+1;j<s.length();j++){
                if (unique(s,i,j)){
                    aa = Math.max(aa,j-i);
                }
            }
        }
        return aa;
    }


    public boolean unique(String s,int start,int end) {
        List<Character> ccs = new ArrayList<>();
        for (int i=start;i<end;i++){
            char c = s.charAt(i);
            if(!ccs.contains(c)){
                ccs.add(c);
            }else {
                return false;
            }
        }
        return true;
    }

    public int lengthOfLongestSubstring1(String s) {
        int ans=0;
       Map<Character,Integer> map = new HashMap<>();
       for (int start=0,end=0;end<s.length();end++){
           if (map.containsKey(s.charAt(end))){
               start = Math.max(start,map.get(s.charAt(end)));
           }
           ans=Math.max(ans,end-start+1);
           map.put(s.charAt(end),end+1);
       }
        return ans;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length!=0&&nums2.length!=0){

            double v = numValue(nums2);
            double v1 = numValue(nums1);
            double v2 = (v + v1) / 2;


            return (numValue(nums2)+numValue(nums1))/2;
        }
        if (nums1.length==0){
            return numValue(nums2);
        }
        if (nums2.length==0){
            return numValue(nums1);
        }
       return 0;
    }

    public double numValue(int[] nums){
        int length=nums.length;

        if (length%2!=0){
            int loc=(nums.length-1)/2;
             return nums[loc];
        }else {
            int loc1=nums.length/2;
            int loc2=loc1-1;
            return (double) (nums[loc1]+nums[loc2])/2;
        }
    }

    public int findRepeatNumber(int[] nums) {
      Set<Integer> set = new HashSet<>();
      int repeat=-1;
        for (int i=0;i<nums.length;i++){
            if (!set.add(nums[i])){
                repeat=nums[i];
                break;
            }
        }
        return repeat;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int length = matrix.length;
        for (int i=0;i<length;i++){
            int[] matrix1 = matrix[i];
            int length1 = matrix1.length;
            for (int j=0;j<length1;j++){
                if (target== matrix1[j]){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        int i = matrix.length-1,j=0;
        while (i>=0&&j<matrix[0].length){
            if (matrix[i][j]>target){
                i--;
            }else if(matrix[i][j]<target){
                j++;
            }else {
                return true;
            }
        }

        return false;
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (Character.isSpaceChar(c)){
                sb.append("%20");
            }else {
                sb.append(c);
            }
            }
        return sb.toString();
        }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        list.add(head.getVal());
        while (head.next!=null){
            list.add(head.next.getVal());
            head.next=head.next.next;
        }
        Collections.reverse(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    Map<Integer,Integer> integerMap = new HashMap<>();
    int[] po;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen!=inLen){
            return null;
        }
        po=preorder;

        for (int i=0;i<inLen;i++){
            integerMap.put(inorder[i],i);
        }

        return recur(0,0,inLen-1);

    }

    public TreeNode recur(int preroot,int inLeft,int inRight){
        if (inLeft>inRight){
            return null;
        }
        //根节点(前序遍历数组得到根节点)
        TreeNode root = new TreeNode(po[preroot]);

        //根节点在中序遍历数组的位置,减1就得到左子树的长度
        Integer integer = integerMap.get(po[preroot]);
        root.left=recur(preroot+1,inLeft,integer-1);
        root.right=recur(integer-inLeft+preroot+1,integer+1,inRight);
        return root;
    }



    LinkedList<Integer> linkedListA,linkedListB;


    public void appendTail(int value) {
        linkedListA.add(value);
    }

    public int deleteHead() {
        if (linkedListA.isEmpty()) return -1;
        while (!linkedListA.isEmpty()){
            linkedListB.addLast(linkedListA.removeLast());
        }

        if (linkedListB.isEmpty()){
            return linkedListB.removeLast();
        }
        return linkedListB.removeLast();
    }

    public int fib(int n) {
        if (n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }

        int first=0,second=1,val=0;

        for (int i=2;i<=n;i++){
            val = first+second;
            first = second;
            second=val%1000000007;
        }
        return val;


    }

    public int minArray(int[] numbers) {
        int i=0,j=numbers.length-1;

        while (i<j){
            int m = (i+j)/2;
            if (numbers[m]>numbers[j]){
                //中间元素大于右侧元素，说明旋转点在右侧
                i=m+1;
            }else if(numbers[m]<numbers[j]){
                //
                j=m;
            }else {
                j--;
            }
        }
        return numbers[i];
    }



    
}
