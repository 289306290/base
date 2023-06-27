package club.wujingjian.base.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.wujingjian.base.po.Video;
import club.wujingjian.base.service.VideoService;
import club.wujingjian.base.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author a123
* @description 针对表【t_video】的数据库操作Service实现
* @createDate 2023-06-27 14:03:21
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




