package club.wujingjian.base.mapper;

import club.wujingjian.base.po.Video;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author a123
* @description 针对表【t_video】的数据库操作Mapper
* @createDate 2023-06-27 14:03:21
* @Entity club.wujingjian.base.po.Video
*/
@Repository
@DS("slave")
public interface VideoMapper extends BaseMapper<Video> {

}




