package com.iweb.mapper;

import com.iweb.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/15 21:30
 */
public interface OrderMapper {
    /** 添加订单
     * @param order 订单对象
     * @return 插入多少条记录
     */
    @Insert("insert into order_(orderCode,address,post,receiver,mobile,userMessage,uid) " +
            "values(#{orderCode},#{address},#{post},#{receiver},#{mobile},#{userMessage},#{uid})")
    int add(Order order);

    /** 删除订单
     * @param id 订单id
     * @return 删除了多少条记录
     */
    @Update("update order_ set status='delete',gmtModified=now() where id = #{id}")
    int delete(Integer id);

    /** 根据id查询一条订单数据
     * @param id 订单id
     * @return 订单对象
     */
    @Select("select * from order_ where id = #{id} and status != 'delete'")
    Order get(int id);

    /** 修改订单
     * @param order 订单对象
     * @return 修改了多少条记录
     */
    @Update("update order_ set orderCode=#{orderCode},address=#{address},post=#{post},receiver=#{receiver}," +
            "mobile=#{mobile},userMessage=#{userMessage},payDate=#{payDate},deliveryDate=#{deliveryDate}," +
            "confirmDate=#{confirmDate},uid=#{uid},status=#{status},gmtModified=now() where id=#{id}")
    int update(Order order);

    /** 查询所有订单数据
     * @return 订单集合
     */
    @Select("select * from order_ where status != 'delete'")
    List<Order> list();

    /** 查询表中一共有多少条数据
     * @return 数量
     */
    @Select("select count(*) from order_ where status != 'delete'")
    int getTotal();

    /** 根据订单编号查询订单
     * @param orderCode 订单编号
     * @return 订单对象
     */
    @Select("select * from order_ where orderCode = #{orderCode} and status != 'delete'")
    Order getByOrderCode(String orderCode);

    /** 根据uid查找订单
     * @param uid 用户id
     * @return 订单集合
     */
    @Select( "select * from order_ where status != 'delete' and uid=#{uid}")
    List<Order> listByUid(int uid);


}
