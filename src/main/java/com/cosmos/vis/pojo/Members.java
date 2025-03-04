package com.cosmos.vis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CosmosBackpacker
 * @since 2024-10-20
 */


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("members")
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "member_id", type = IdType.AUTO)
    private Long memberId;

    private Long userId;

    private String viplevel;

    private LocalDateTime registrationDate;

    private LocalDateTime expirationDate;


}
