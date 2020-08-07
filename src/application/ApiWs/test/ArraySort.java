package application.ApiWs.test;

public class ArraySort implements Runnable {
    private String num;

    public ArraySort(int num) {
        this.num = num + "";
    }

    public static void main(String[] args) {
        int[] nums = {11,3,2,998,5455,998,5455,998,5455,998,5455,998,5455,998,5455,1,152,990,12};
        for (int i = 0;i < nums.length;i ++) {
            new Thread(new ArraySort(nums[i])).start();
        }
    }

    public void run() {
        try {
            Thread.sleep(Integer.parseInt(num));
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
