public class Node<T> {
        T student;
        String first_name;
        String second_name;
        Node<T> left,right;
        Node(T str){
            student = str;
            left = null;
            right = null;
        }
   }


