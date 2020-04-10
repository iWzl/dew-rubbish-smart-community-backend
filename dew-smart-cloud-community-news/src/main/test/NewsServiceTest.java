import com.upuphub.dew.community.news.DewNewsApplication;
import com.upuphub.dew.community.news.bean.po.NewsDetailPO;
import com.upuphub.dew.community.news.dao.NewsRepositoryDao;
import com.upuphub.dew.community.news.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DewNewsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsServiceTest {
    @Autowired
    NewsRepositoryDao newsRepositoryDao;
    @Autowired
    NewsService newsService;

    @Test
    public void saveNewsDetail(){
        NewsDetailPO newsDetailPO = new NewsDetailPO();
        newsDetailPO.setCategory("我是Category");
        newsDetailPO.setCreateBy(10501L);
        newsDetailPO.setCreateDate(System.currentTimeMillis());
        newsDetailPO.setNewsId(378921658936829547L);
        newsDetailPO.setNewsType(0);
        newsDetailPO.setPayloadType(3);
        newsDetailPO.setTitle("我是Title");
        newsDetailPO.setFrontCoverImages(Collections.singletonList("http://img2.imgtn.bdimg.com/it/u=3034276324,1517112842&fm=26&gp=0.jpg"));
        newsDetailPO.setPayload("# 我是正文 \n ## 我是副标题 \n 我是正文");
        newsRepositoryDao.save(newsDetailPO);
    }

    @Test
    public void fetchNewDetails(){
        newsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(0,10);
    }
}
