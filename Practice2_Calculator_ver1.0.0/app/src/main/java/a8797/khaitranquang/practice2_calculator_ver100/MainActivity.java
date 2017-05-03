package a8797.khaitranquang.practice2_calculator_ver100;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    String textMath = "", textAns = "0", screenMath = "";
//    int checkSubmit = 0;

    private EditText edtInput;
    private TextView tvResult;

    private Button btnNumber0;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;

    private Button btnCong;
    private Button btnTru;
    private Button btnNhan;
    private Button btnChia;

    private Button btnResult;
    private Button btnPoint;
    private Button btnClear;
    private Button btnClearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickView();
    }

    private void initWidget(){
        btnNumber0 = (Button) findViewById(R.id.btnNumber0);
        btnNumber1 = (Button) findViewById(R.id.btnNumber1);
        btnNumber2 = (Button) findViewById(R.id.btnNumber2);
        btnNumber3 = (Button) findViewById(R.id.btnNumber3);
        btnNumber4 = (Button) findViewById(R.id.btnNumber4);
        btnNumber5 = (Button) findViewById(R.id.btnNumber5);
        btnNumber6 = (Button) findViewById(R.id.btnNumber6);
        btnNumber7 = (Button) findViewById(R.id.btnNumber7);
        btnNumber8 = (Button) findViewById(R.id.btnNumber8);
        btnNumber9 = (Button) findViewById(R.id.btnNumber9);

        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);

        btnResult = (Button) findViewById(R.id.btnResult);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClearAll = (Button) findViewById(R.id.btnClearAll);
    }

    public void setEventClickView(){
        tvResult = (TextView) findViewById(R.id.tvResult);
        edtInput = (EditText) findViewById(R.id.edtInput);

        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);

        btnResult.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String[] elementMath = null;
        switch (v.getId()){
            case R.id.btnNumber0:
                edtInput.append("0");
                break;
            case R.id.btnNumber1:
                edtInput.append("1");
                break;
            case R.id.btnNumber2:
                edtInput.append("2");
                break;
            case R.id.btnNumber3:
                edtInput.append("3");
                break;
            case R.id.btnNumber4:
                edtInput.append("4");
                break;
            case R.id.btnNumber5:
                edtInput.append("5");
                break;
            case R.id.btnNumber6:
                edtInput.append("6");
                break;
            case R.id.btnNumber7:
                edtInput.append("7");
                break;
            case R.id.btnNumber8:
                edtInput.append("8");
                break;
            case R.id.btnNumber9:
                edtInput.append("9");
                break;

            case R.id.btnCong:
                edtInput.append("+");
                break;
            case R.id.btnTru:
                edtInput.append("-");
                break;
            case R.id.btnNhan:
                edtInput.append("*");
                break;
            case R.id.btnChia:
                edtInput.append("/");
                break;



            case R.id.btnResult:
                submit();
                break;


            case R.id.btnPoint:
                edtInput.append(".");
                break;

            case R.id.btnClear:
//                String stringAfter = Clear(edtInput.getText().toString());
//                edtInput.setText(stringAfter);

                /*Action delete for btnClear*/
                /*BaseInputConection: Base class for implementors of the InputConnection
                 interface taking care of most of the common behavior for providing a
                 connection to an Editable. Implementors of this class will want to
                 be sure to implement getEditable() to provide access to their own
                 editable object, and to refer to the documentation in InputConnection.
                */
                BaseInputConnection tfInput = new BaseInputConnection(edtInput, true);
                tfInput.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnClearAll:
                edtInput.setText("");
                break;

        }
    }

//    public String Clear(String number){
//        int len = number.length();
//        String temp = number.substring(0, len-1);
//        return temp;
//    }

    public void error(){
        tvResult.setText("Math Error!");
        //textAns = textMath =screenMath ="";
    }

    public void submit (){
        String textMath = "", textAns = "0", screenMath = "";
        int checkSubmit = 0;
        String[] elementMath = null;
        Postfix ITP = new Postfix();
        textMath = edtInput.getText().toString();
        if(textMath.length() > 0){
            try{
                if(ITP.check_error == false) elementMath = ITP.processString(textMath);    //Tach bieu thuc thanh cac phan tu
                if(ITP.check_error == false) elementMath = ITP.infixToPostfix(elementMath);    //dua ve postfix
                if(ITP.check_error == false) textAns = ITP.valueMath(elementMath); //Ket qua
                tvResult.setText(textAns);
                textMath = textAns;
                screenMath= textAns;
                checkSubmit = 1;
            }
            catch (Exception e){
                error();
          }
            if(ITP.check_error) error();
        }
    }
}


class Postfix{
    boolean check_error = false;     //Kiem tra la ki tu dau la am hay duong, kiem tra loi
//    boolean isSytaxError = false;   //Kiem tra loi cu phap trong bieu thuc input
    //Chuan hoa so
    public String standardizeDouble(double number){
        int a = (int) number;
        if(a == number){
            return Integer.toString(a);
        }
        else return Double.toString(number);
    }

    //Kiem tra xem ki tu c co la so khong?
    public boolean isNumber(char c){
        if(Character.isDigit(c)) return true;
        else return false;
    }

    //Chuyen So sang chuoi
    public String numberToString(double number){
        return standardizeDouble(number);
    }

    //Chuyen Chuoi sang so
    public double stringToNumber(String s){
        return Double.parseDouble(s);
    }

    //Kiem tra xem ki tu c co phai toan tu khong
    public boolean isOperator(char c){
        char operator[] = {'+', '-', '*', '/', '(', ')', '~'};  //Dau "~" thay cho dau - trong so am
        Arrays.sort(operator);
        if(Arrays.binarySearch(operator, c) > -1) return true;
        else return false;
    }

    //Kiem tra xem co phai toan tu 1 ngoi
    public boolean isOneMath(char c){
        char operatorOne[] = {'~', '(', 's', 'c', 't'};     //s,c,t la sin cos tan
        Arrays.sort(operatorOne);
        if(Arrays.binarySearch(operatorOne, c) > -1) return true;
        else return false;
    }

    //Thiet lap do uu tien cho cac toan tu
    public int priority(char c){
        if(c == '+' || c == '-') return 1;
        else if(c == '*' || c == '/') return 2;
        else if(c == '~') return 3;
        else return 0;
    }

    //Chuan hoa bieu thuc Infix (Trung to)
    public String standardize(String infix){
        String s1 = "";
        infix = infix.trim();           //Loai bo dau cau o dau va cuoi bieu thuc
        infix = infix.replaceAll("\\s+"," ");       //Thay nhieu hon 1 dau cach trong bieu thuc thanh
                                                    //1 dau cach

        //Kiem tra so dau dong mo ngoac co bang nhau khong? Neu khong => Loi cu phap
        int open = 0, close = 0;
        for (int i = 0; i < infix.length(); i++){
            char c = infix.charAt(i);
            if(c == '(') open++;
            if(c == ')') close++;
        }
        if(open != close) check_error = true;

        for (int i = 0; i < infix.length(); i++){
            //Loi cu phap neu dang ...)(...  hoac 3(...  hoac ..+)... hoac ..)3...
            if (i > 0 && infix.charAt(i) == '(' && (isNumber(infix.charAt(i-1)) || infix.charAt(i-1) ==')'))
                check_error = true;
            else if (i > 0 && infix.charAt(i) == ')' && (isOperator(infix.charAt(i-1)) || isNumber(infix.charAt(i-1))))
                check_error = true;
            //Loi cu phap neu ki tu cuoi cung la toan tu, hoac bat dau la *, /
            // hoac co 2 toan tu * hoac / lien nhau
            else if (isOperator(infix.charAt(infix.length()-1)) || infix.charAt(0) == '*' || infix.charAt(0) == '/')
                check_error = true;
            else if (i > 0 && (infix.charAt(i) == '*' || infix.charAt(i) == '/' ) &&
                         (infix.charAt(i-1) == '*' || infix.charAt(i-1) == '/' ))
                check_error = true;
            //Chuan hoa so am
            else if ((i == 0 || (i>0 && !isNumber(infix.charAt(i-1)))) && infix.charAt(i) == '-' && isNumber(infix.charAt(i+1)))
                s1 = s1 + "~";
            else if ((i == 0 || (i>0 && !isNumber(infix.charAt(i-1)))) && infix.charAt(i) == '+' && isNumber(infix.charAt(i+1))) {
                s1 = s1 + ""; // check dau +
            }
            else s1 = s1 + infix.charAt(i);
        }
        return s1;
    }

    //Xu li bieu thuc nhap vao thanh cac phan tu
    public String[] processString (String sMath){
        String s1 = "", elementMath[] = null;
        sMath = standardize(sMath);

        if(check_error == true) return null;
        else{
            for (int i = 0; i < sMath.length(); i++){
                char c = sMath.charAt(i);
                if(!isOperator(c)) s1= s1 + c;
                else s1= s1 + " " + c + " ";
            }
        }

        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," ");     //Chuan hoa s1
        elementMath = s1.split(" ");        //Tach s1 thanh cac phan tu
        return elementMath;
    }

    //Chuyen bieu thuc trung to thanh hau to
    public String[] infixToPostfix (String [] elementMath){
        String s1 = "", E[];
        Stack <String> S = new Stack <String>();

        for (int i = 0; i < elementMath.length; i++) {
            char c = elementMath[i].charAt(0);      //c la ki tu dau tien cua mou phan tu

            if(!isOperator(c)){                     //Neu c khong la toan tu, xuat ra s1
                s1 = s1 + elementMath[i] + " ";
            }
            else{       //c la tian tu
                if(c == '(') S.push(elementMath[i]);    //c la ( thi day vao Stack
                else{
                    if (c == ')'){                      //Neu c la ")"
                        char c1;
                        do{
                            c1= S.peek().charAt(0);     //c1 la ki tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + S.peek() + " ";
                            S.pop();
                        }while (c1 != '(');
                    }
                    else{
                        //Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                        while (!S.isEmpty() && priority(S.peek().charAt(0)) >= priority(c)){
                            s1 = s1 + S.pop() + " ";
                        }
                        S.push(elementMath[i]);     //Day phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()) s1 = s1 + S.pop() + " ";   //Neu Stack con phan tu
        E = s1.split(" ");  //Tach s1 thanh cac phan tu
        return E;
    }

    //Tinh gia tri bieu thuc dua vao postFix
    public String valueMath (String[] elementMath) {
        Stack<Double> S = new Stack<Double>();
        double result = 0.0;
        for (int i = 0; i < elementMath.length; i++) {
            char c = elementMath[i].charAt(0);  //Ki tu dau tien cua moi phan tu

            //Neu la toan hang thi push vao Stack
            if (!isOperator(c)) S.push(Double.parseDouble(elementMath[i]));
                //Neu la toan tu  2 ngoi thi pop 2 toan hang trong Stack ra va tinh gia tri cua chung
                //dua vao toan tu nay. Push lai ket qua vao Stack
            else {

                //Tinh toan voi cac toan tu 1 ngoi
                double num1 = S.pop();
                switch (c) {
                    case '~':
                        result = -num1;
                        break;
                    //vv.... (Bo sung sau)
                    default:
                        break;
                }
                //Tinh toan voi cac toan tu 2 ngoi
                if (!S.isEmpty()) {
                    double num2 = S.peek();
                    switch (c) {
                        case '+':
                            result = num2 + num1;
                            S.pop();
                            break;
                        case '-':
                            result = num2 - num1;
                            S.pop();
                            break;
                        case '*':
                            result = num2 * num1;
                            S.pop();
                            break;
                        case '/': {
                            if (num1 != 0) result = num2 / num1;
                            else check_error = true;
                            S.pop();
                            break;
                        }
                        //case:.......(Bo sung sau)
                    }

                }
                S.push(result);
            }

        }
        return numberToString(S.pop());
    }
}

//class InfixToPostfix{
//    boolean check_error = false;  // kiem tra ky tu dau tien la am hay duong, kiem tra loi
//
//    public String standardizeDouble(double num){ //chuan hoa so
//        int a = (int)num;
//        if (a == num)
//            return Integer.toString(a);
//        else return Double.toString(num);
//    }
//
//    public boolean isCharPi(char c){ //kiem tra ky tu c la Pi hay khong
//        if (c == 'π') return true;
//        else return false;
//    }
//
//    public boolean isNumPi(double num){ //kiem tra so num la Pi hay khong
//        if (num == Math.PI) return true;
//        else return false;
//    }
//
//    public boolean isNum(char c){	//kiem tra ky tu c co la so khong (pi cung la so)
//        if (Character.isDigit(c) || isCharPi(c)) return true;
//        else return false;
//    }
//
//    public String NumToString(double num){ //chuyen so sang chuoi
//        if (isNumPi(num)) return "π";
//        else return standardizeDouble(num);
//    }
//
//    public double StringToNum(String s){ 	//Chuoi sang so
//        if (isCharPi(s.charAt(0))) return Math.PI;
//        else return Double.parseDouble(s);
//    }
//
//    public boolean isOperator(char c){ 	// kiem tra xem co phai toan tu
//        char operator[] = { '+', '-', '*', '/', '^', '~', 's', 'c', 't', '@', '!', '%', ')', '('}; //~ thay cho dau am (-)
//        Arrays.sort(operator);
//        if (Arrays.binarySearch(operator, c) > -1)
//            return true;
//        else return false;
//    }
//    public int priority(char c){		// thiet lap thu tu uu tien
//        switch (c) {
//            case '+' : case '-' : return 1;
//            case '*' : case '/' : return 2;
//            case '~' : return 3;
//            case '@' : case '!' : case '^' : return 4;
//            case 's' : case 'c' : case 't' : return 5;
//        }
//        return 0;
//    }
//
//    public boolean isOneMath(char c){ 	// kiem tra toan tu 1 ngoi
//        char operator[] = { 's', 'c', 't', '@', '('}; //~ thay cho dau am (-)
//        Arrays.sort(operator);
//        if (Arrays.binarySearch(operator, c) > -1)
//            return true;
//        else return false;
//    }
//
//    public String standardize(String s){ //chuan hoa bieu thuc
//        String s1 = "";
//        s = s.trim();
//        s = s.replaceAll("\\s+"," "); //	chuan hoa s
//        int open = 0, close = 0;
//        for (int i=0; i<s.length(); i++){
//            char c = s.charAt(i);
//            if (c == '(') open++;
//            if (c == ')') close++;
//        }
//        for (int i=0; i< (open - close); i++) // them cac dau ")" vao cuoi neu thieu
//            s += ')';
//        for (int i=0; i<s.length(); i++){
////            if (i>0 && isOneMath(s.charAt(i)) && (s.charAt(i-1) == ')' || isNum(s.charAt(i-1)))) s1 = s1 + "*"; //	chuyen ...)(... thanh ...)*(...
//            if ((i == 0 || (i>0 && !isNum(s.charAt(i-1)))) && s.charAt(i) == '-' && isNum(s.charAt(i+1))) {
//                s1 = s1 + "~"; // check so am
//            }
//        	else if ((i == 0 || (i>0 && !isNum(s.charAt(i-1)))) && s.charAt(i) == '+' && isNum(s.charAt(i+1))) {
//        		s1 = s1 + ""; // check dau +
//        	}
//            else if (i>0 && (isNum(s.charAt(i-1)) || s.charAt(i-1) == ')') && isCharPi(s.charAt(i))) s1 = s1 + "*" + s.charAt(i);
//                // VD hoac 6π , ...)π chuyen sang 6*π , ...)*π
//            else s1 = s1 + s.charAt(i);
//        }
//        return s1;
//    }
//
//    public String[] processString(String sMath){ // xu ly bieu thuc nhap vao thanh cac phan tu
//        String s1 = "", elementMath[] = null;
//        sMath = standardize(sMath);
//        InfixToPostfix  ITP = new InfixToPostfix();
//        for (int i=0; i<sMath.length(); i++){
//            char c = sMath.charAt(i);
//            if (i<sMath.length()-1 && isCharPi(c) && !ITP.isOperator(sMath.charAt(i+1))){ // error neu co dang π3
//                check_error = true;
//                return null;
//            }
//            else
//            if (!ITP.isOperator(c))
//                s1 = s1 + c;
//            else s1 = s1 + " " + c + " ";
//        }
//        s1 = s1.trim();
//        s1 = s1.replaceAll("\\s+"," "); //	chuan hoa s1
//        elementMath = s1.split(" "); //tach s1 thanh cac phan tu
//        return elementMath;
//    }
//
//    public String[] postfix(String[] elementMath){
//        InfixToPostfix  ITP = new InfixToPostfix();
//        String s1 = "", E[];
//        Stack <String> S = new Stack<String>();
//        for (int i=0; i<elementMath.length; i++){ 	// duyet cac phan tu
//            char c = elementMath[i].charAt(0);		// c la ky tu dau tien cua moi phan tu
//
//            if (!ITP.isOperator(c)) 				// neu c khong la toan tu
//                s1 = s1 + elementMath[i] + " ";		// xuat elem vao s1
//            else{									// c la toan tu
//                if (c == '(') S.push(elementMath[i]);	// c la "(" -> day phan tu vao Stack
//                else{
//                    if (c == ')'){						// c la ")"
//                        char c1;						//duyet lai cac phan tu trong Stack
//                        do{
//                            c1 = S.peek().charAt(0);	// c1 la ky tu dau tien cua phan tu
//                            if (c1 != '(') s1 = s1 + S.peek() + " "; 	// trong khi c1 != "("
//                            S.pop();
//                        }while (c1 != '(');
//                    }
//                    else{
//                        // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
//                        while (!S.isEmpty() && ITP.priority(S.peek().charAt(0)) >= ITP.priority(c))
//                            s1 = s1 + S.pop() + " ";
//                        S.push(elementMath[i]); // 	dua phan tu hien tai vao Stack
//                    }
//                }
//            }
//        }
//        while (!S.isEmpty()) s1 = s1 + S.pop() + " "; // Neu Stack con phan tu thi day het vao s1
//        E = s1.split(" ");	//	tach s1 thanh cac phan tu
//        return E;
//    }
//
//    public String valueMath(String[] elementMath){
//        Stack <Double> S = new Stack<Double>();
//        InfixToPostfix  ITP = new InfixToPostfix();
//        double num = 0.0;
//        for (int i=0; i<elementMath.length; i++){
//            char c = elementMath[i].charAt(0);
//            if (isCharPi(c)) S.push(Math.PI);	// neu la pi
//            else{
//                if (!ITP.isOperator(c)) S.push(Double.parseDouble(elementMath[i])); //so
//                else{	// toan tu
//
//                    double num1 = S.pop();
//                    switch (c) {
//                        case '~' : num = -num1; break;
//                        case 's' : num = Math.sin(num1); break;
//                        case 'c' : num = Math.cos(num1); break;
//                        case 't' : num = Math.tan(num1); break;
//                        case '%' : num = num1/100; break;
//                        case '@' : {
//                            if (num1 >=0){
//                                num = Math.sqrt(num1); break;
//                            }
//                            else check_error = true;
//                        }
//                        case '!' : {
//                            if (num1 >= 0 && (int)num1 == num1){
//                                num = 1;
//                                for (int j=1; j<=(int)num1; j++)
//                                    num = num * j;
//                            }
//                            else check_error = true;
//                        }
//                        default : break;
//                    }
//                    if (!S.empty()){
//                        double num2 = S.peek();
//                        switch (c) {
//                            //-----------------------
//                            case '+' : num = num2 + num1; S.pop(); break;
//                            case '-' : num = num2 - num1; S.pop(); break;
//                            case '*' : num = num2 * num1; S.pop(); break;
//                            case '/' : {
//                                if (num1 != 0) num = num2 / num1;
//                                else check_error = true;
//                                S.pop(); break;
//                            }
//                            case '^' : num = Math.pow(num2, num1); S.pop(); break;
//                        }
//                    }
//                    S.push(num);
//                }
//            }
//        }
//        return NumToString(S.pop());
//    }
//}
//
