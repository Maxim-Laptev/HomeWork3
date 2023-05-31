
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tree<Integer,String> tree=new tree<>();
		tree.add(4, "4");
		System.out.println(tree.find(4));
		tree.remove(4);
		System.out.println(tree.find(4));
	}

}
