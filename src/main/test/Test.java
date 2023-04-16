import com.iweb.entity.Category;
import com.iweb.entity.Order;
import com.iweb.entity.OrderItem;
import com.iweb.entity.Product;
import com.iweb.mapper.CategoryMapper;
import com.iweb.mapper.OrderItemMapper;
import com.iweb.mapper.OrderMapper;
import com.iweb.mapper.ProductMapper;
import com.iweb.util.OrderCodeUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yang
 * @date 2023/4/15 17:09
 */
public class Test {
    // 定义配置文件路径
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession session;
    CategoryMapper categoryMapper;
    ProductMapper productMapper;
    OrderMapper orderMapper;
    OrderItemMapper orderItemMapper;

    @Before
    public void init() throws IOException {
        // 建立输入流读取配置文件
        inputStream = Resources.getResourceAsStream(resource);
        // 实例化mybatis一级缓存
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 基于一级缓存实例化二级缓存
        session = sqlSessionFactory.openSession();
        categoryMapper = session.getMapper(CategoryMapper.class);
        productMapper = session.getMapper(ProductMapper.class);
        orderMapper = session.getMapper(OrderMapper.class);
        orderItemMapper = session.getMapper(OrderItemMapper.class);
    }

    @org.junit.Test
    public void testList(){
        List<Category> list = categoryMapper.list();
        System.out.println(list);
    }

    @org.junit.Test
    public void test(){
//        Category category = new Category();
//        category.setName("测试mybatis分类");
//        System.out.println(categoryMapper.add(category));
//        session.commit();
//        testList();
//        Category category = new Category();
//        category.setName("测试修改分类2");
//        category.setId(87);
//        System.out.println( categoryMapper.update(category));
//        System.out.println(categoryMapper.updateTime(category.getId()));
//        session.commit();
//        testList();
//        System.out.println(categoryMapper.delete(87));
//        session.commit();
//        System.out.println(categoryMapper.get(83));

//        Order order = new Order();
//        order.setId(26);
//        order.setOrderCode(OrderCodeUtil.getOrderId(1));
//        order.setAddress("测试");
//        order.setPost("测试1");
//        order.setReceiver("测试安倍");
//        order.setMobile("156456测试123");
//        order.setUserMessage("hhh测试");
//        order.setUid(1);
//        order.setStatus("waitPay");
//        orderMapper.delete(26);
//        session.commit();
//        orderMapper.listByUid(1);
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(24);
//        orderItem.setUid(2);
//        orderItem.setOid(-1);
//        orderItem.setPid(442);
//        orderItem.setNumber(52);

    }

    @org.junit.Test
    public void testProductList(){
//        List<Product> list = productMapper.list(83);
//        System.out.println(productMapper.getTotal());
//        productMapper.get(8);
//        Product product = new Product();
//        product.setId(968);
//        product.setName("测试修改商品6");
//        product.setSubTitle("测试商品小标题8");
//        product.setOriginalPrice(new BigDecimal(79));
//        product.setPromotePrice(new BigDecimal(66));
//        product.setStock(35);
//        product.setCid(83);
//        productMapper.update(product);
//        productMapper.add(product);
        System.out.println(productMapper.getTotalByCid(83));
        session.commit();
    }
}
