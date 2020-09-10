public abstract class StrBuilder {

    public static String createShortDescr(String str){
        int cnt = 0;
        int countString = 0;
        char[] chars = str.toCharArray();
        char[] arrChar = new char[35];
        String[] charArray = new String[10];
        for (char cha : chars) {
            if (cnt < 35) {
                arrChar[cnt] = cha;
                cnt++;
            } else {
                charArray[countString] = new String(arrChar).concat("\n");
                countString++;
                cnt = 0;
                for(int y = 0; y<arrChar.length-1; y++){
                    arrChar[y] = ' ';
                }
                arrChar[cnt] = cha;
                cnt = 1;
            }
        }

        char dopChar[] = new char[cnt];
        for(int z = 0; z<cnt; z++){
            dopChar[z] = arrChar[z];
        }
        charArray[countString] = new String(dopChar);

        str  = " ";
        for (String strr : charArray) {
            if (strr != null) {
                str = str.concat(strr);
            }
        }
        return str;
    }

    public static String createDescr(String str){
        int strLength = 50;
        int cnt = 0;
        int countString = 0;
        char[] chars = str.toCharArray();
        char[] arrChar = new char[strLength];
        String[] charArray = new String[100];
        for (char cha : chars) {
            if (cnt < strLength) {
                arrChar[cnt] = cha;
                cnt++;
            } else {
                charArray[countString] = new String(arrChar).concat("\n");
                countString++;
                cnt = 0;
                for(int y = 0; y<arrChar.length-1; y++){
                    arrChar[y] = ' ';
                }
                arrChar[cnt] = cha;
                cnt = 1;
            }
        }

        char dopChar[] = new char[cnt];
        for(int z = 0; z<cnt; z++){
            dopChar[z] = arrChar[z];
        }
        charArray[countString] = new String(dopChar);

        str  = " ";
        for (String strr : charArray) {
            if (strr != null) {
                str = str.concat(strr);
            }
        }
        return str;
    }
}
