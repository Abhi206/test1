package tester;
import static utilss.HibernateUtils.*;
import org.hibernate.SessionFactory;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
public class Testing 
{
	public static void main(String[] args) 
	{
		SessionFactory factor = getSf();
		System.out.println("Hibernate booted");
	}
}
