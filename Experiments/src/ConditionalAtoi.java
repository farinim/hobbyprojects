//https://leetcode.com/problems/string-to-integer-atoi/
public class ConditionalAtoi {

    public int atoi(String s) {
        char sign='+';
        boolean start= false;
        int num = 0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '+' || c== '-'){
                if(!start){
                    sign = c;
                    start = true;
                }else{
                    break;
                }
                continue;
            }

            /*if(c == ' ' || c == '.'){
                if(start) {
                    break;
                }
            }*/
            if(c >='0' && c <= '9'){
                if(!start){
                    start = true;
                }
                long temp = (long)num *10 + (c - 48);
                num = (int)temp;

                if(temp != num){
                    if(sign == '-'){
                        return -2147483648;
                    }else{
                        return 2147483647;
                    }
                }
            }else{
                if(c!= ' ' && !start){
                    return 0;
                }
                if(start) {
                    break;
                }
            }
        }
        if(sign == '-'){
            num = num * -1;
        }
        return num;

    }

    public static void main(String [] args){

        ConditionalAtoi atoi = new ConditionalAtoi();
        System.out.println(atoi.atoi("-6147483648"));
        System.out.println(atoi.atoi("42"));
        System.out.println(atoi.atoi("   -42"));
        System.out.println(atoi.atoi("words and 987"));
        System.out.println(atoi.atoi("2340 words"));
        System.out.println(atoi.atoi("2147483648"));
        System.out.println(atoi.atoi("-91283472332"));
        System.out.println(atoi.atoi("-2147483648"));
    }
}
