package com.dbc.user.sys.mapper;

import com.dbc.user.sys.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dbc
 * @since 2018-11-14
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<Map<String, String>> getKeyValueListByCode(String code);

}
