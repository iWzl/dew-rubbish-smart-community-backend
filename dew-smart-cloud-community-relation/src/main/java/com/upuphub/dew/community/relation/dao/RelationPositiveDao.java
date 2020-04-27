package com.upuphub.dew.community.relation.dao;

import com.upuphub.dew.community.relation.bean.po.RelationDetailPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationPositiveDao {
    /**
     * 插入新的用户关系
     *
     * @param relationDetail 用户关系明细
     */
    void insertNewRelation(@Param("relationDetail") RelationDetailPO relationDetail);

    /**
     * 正方向好友关系
     *
     * @param sponsorUin   发起者uin
     * @param recipientUin 接受者uin
     * @return 查询到的两个用户之间的关系属性
     */
    RelationDetailPO selectHistoryRelationPositiveDetailByUin(@Param("sponsorUin") Long sponsorUin, @Param("recipientUin") Long recipientUin);

    /**
     * 反方向好友关系
     *
     * @param sponsorUin   发起者uin
     * @param recipientUin 接受者uin
     * @return 查询到的两个用户之间的关系属性
     */
    RelationDetailPO selectHistoryRelationReverseDetailByUin(@Param("sponsorUin") Long sponsorUin, @Param("recipientUin") Long recipientUin);

    /**
     * 更新用户的关系属性类型
     *
     * @param sponsorUin   发起的用户关系类型
     * @param recipientUin 接受的用户关系类型
     * @param relationType 用户关系属性类型
     */
    void updateRelationTypeByUinAndTypeNumber(@Param("sponsorUin") Long sponsorUin, @Param("recipientUin") Long recipientUin, @Param("relationType") Integer relationType);

    /**
     * 反转关系属性类型
     *
     * @param sponsorUin   发起者uin
     * @param recipientUin 接受者uin
     * @param relationType 关系类型
     */
    void updateReversionRelationType(@Param("sponsorUin") Long sponsorUin, @Param("recipientUin") Long recipientUin, @Param("relationType") int relationType);

    /**
     * 查询好友的Match好友关系
     *
     * @param uin 用户UIN
     * @return 查询到的关系详细结果
     */
    List<RelationDetailPO> selectMatchRelationDetailsByUin(@Param("uin") Long uin);

    /**
     * 查询好友的指定关系明细
     *
     * @param searchUin     查询的UIN
     * @param relationTypes 所有的关系类型
     * @param reverse       查询正向或反向的好友关系
     * @return 查询到的好友关系处理结果
     */
    List<RelationDetailPO> selectRelationDetailsByUinAndRelationType(@Param("searchUin") Long searchUin, @Param("relationTypes") List<Integer> relationTypes, @Param("reverse") boolean reverse);
}
