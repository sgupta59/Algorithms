package dynamicprogramming;

import java.util.Scanner;

public class StringEditDistanceRecursion {
	
		static class cell
		{
			int cost, parent;
		}
		static final int MAXLEN = 15;
		static cell m[][] = new cell[MAXLEN][MAXLEN];
		static final int MATCH = 0;
		static final int INSERT = 1;
		static final int DELETE = 2;
		
		static int indel(char c)
		{
			return 1;
		}
		
		static int match(char c, char d)
		{
			if(c==d) return 0;
			return 1;
		}
		
		static void row_init(int i)
		{
			m[0][i].cost = i;
			if (i>0) m[0][i].parent =  INSERT;
			else m[0][i].parent = -1;
		}
		
		static void column_init(int i)
		{
			m[i][0].cost = i;
			if (i>0) m[i][0].parent = DELETE;
			else m[0][i].parent = -1;
		}

		static int string_compare(String s, String t, int i, int j)
		{
			String s1 = s.substring(0,i);
			String s2 = t.substring(0,j);
			//System.out.println("Comparing: " +   "["+s1 + ", " + s2+ "]");
			int k, lowest_cost;
			int opt[] = new int [3];
			if(i==0) return j*indel(' ');
			if(j==0) return i*indel(' ');
			opt[MATCH] = string_compare(s,t,i-1,j-1) + match(s.charAt(i),t.charAt(j));
			opt[INSERT] = string_compare(s,t,i,j-1) + indel(t.charAt(j));
			opt[DELETE] = string_compare(s,t,i-1,j) + indel(s.charAt(i));
			lowest_cost = opt[MATCH];
			int best = MATCH;
			for(k=INSERT;k<=DELETE;k++)
				if(opt[k]<lowest_cost)
				{
					lowest_cost=opt[k];
					best = k;
				}
			m[i][j].cost=lowest_cost;
			m[i][j].parent = best;
			return lowest_cost;
		}
		
		static void print_matrix(String s, String t, boolean costQ)
		{
			int x = s.length();
			int y = t.length();
			System.out.printf("   ");
			for(int i=0;i<y;i++)
				System.out.printf("  %c",t.charAt(i));
			System.out.printf("\n");
			for(int i=0; i<x; i++) {
				System.out.printf("%c: ",s.charAt(i));
				for(int j=0; j<y; j++) {
					if(costQ)
						System.out.printf(" %2d",m[i][j].cost);
					else
						System.out.printf(" %2d",m[i][j].parent);
					}
				System.out.printf("\n");
			}
		}
		
		static void reconstruct_path(String s, String t, int i, int j)
		{
			if((i==0&&j==0)||m[i][j].parent == -1) return;
			if(i*j==0&&(i!=0||j!=0))
			{
				System.out.printf("S");
				return;
			}
			if(m[i][j].parent == MATCH) {
				reconstruct_path(s,t,i-1,j-1);
				match_out(s, t, i, j);
				return;
			}
			if (m[i][j].parent == INSERT) {
				reconstruct_path(s,t,i,j-1);
				insert_out(t,j);
				return;
			}
			if (m[i][j].parent == DELETE) {
				reconstruct_path(s,t,i-1,j);
				delete_out(s,i);
				return;
			}
			return;
		}
		
		static void match_out(String s, String t, int i, int j)
		{
			if(s.charAt(i) == t.charAt(j)) System.out.printf("M");
			else System.out.printf("S");
		}
		
		static void insert_out(String t, int j)
		{
			System.out.printf("I");
		}
		
		static void delete_out(String s, int i)
		{
			System.out.printf("D");
		}

		static public void main(String[] args)
		{
			for(int i=0;i<MAXLEN;i++)
			{
				for(int j=0;j<MAXLEN;j++)
					m[i][j]=new cell();
				row_init(i);
				column_init(i);
			}
			String s = new String();
			String t = new String();
			Scanner sc = new Scanner(System.in);
			s=sc.next();
			t=sc.next();
			s=" " + s;
			t=" " + t;
			System.out.printf("matching cost = %d \n",string_compare(s,t,s.length()-1,t.length()-1));
			print_matrix(s,t,true);
			System.out.printf("\n");
			print_matrix(s,t,false);
			int i,j;
			System.out.printf("%d ",(i=(s.length()-1)));
			System.out.printf("%d\n",(j=(t.length()-1)));
			reconstruct_path(s,t,i,j);
			System.out.printf("\n");
		}
	}