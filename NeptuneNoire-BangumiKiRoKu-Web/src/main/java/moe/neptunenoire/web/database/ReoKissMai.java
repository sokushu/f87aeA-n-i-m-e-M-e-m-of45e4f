package moe.neptunenoire.web.database;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.controller.error.BlogNotFoundException;
import moe.neptunenoire.web.controller.error.HomeNotFoundException;
import moe.neptunenoire.web.controller.error.IAmError;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.table.blog.BlogArticle;
import moe.neptunenoire.web.utils.StringUtil;
import moe.neptunenoire.web.utils.UserID;

public class ReoKissMai extends DataSet {

	private StringUtil stringUtil = new StringUtil();

	/**
	 *
	 * @param maiKissReo
	 * @param redis
	 */
	public ReoKissMai(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		super(maiKissReo, redis);
	}

	@Override
	public List<Map<String, Object>> Anime_FindAllAnime() {
		List<Map<String, Object>> data = getAllData(DataType.Anime);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.Anime_FindAllAnime();
			if (!stringUtil.isNull(data)) {
				data.forEach(var->{
					saveData(DataType.Anime, (long)(var.get("anime_id")), var);
				});
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> Anime_FindByAnimeID(String animeid) throws BangumiNotFoundException {
		long animeID;
		try {
			animeID = Long.parseLong(animeid);
		} catch (Exception e) {
			throw new BangumiNotFoundException(e.getMessage());
		}
		Map<String, Object> data = getData(DataType.Anime, animeID);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.Anime_FindByAnimeID(animeid);
			if (stringUtil.isNull(data)) {
				throw new BangumiNotFoundException("非常抱歉，没有找到"+animeid);
			}else {
				saveData(DataType.Anime, animeID, data);
			}
		}
		return data;
	}

	@Override
	public void Anime_AddAnime(moe.neptunenoire.web.table.Anime anime) {
		maiKissReo.Anime_AddAnime(anime);
		String animeid = anime.getAnime_id().toString();
		try {
			Map<String, Object> data = maiKissReo.Anime_FindByAnimeID(animeid);
			if (stringUtil.isNull(data)) {
				throw new IAmError("动画数据保存失败，原因未知");
			}else {
				saveData(DataType.Anime, anime.getAnime_id(), data);
			}
		} catch (BangumiNotFoundException e) {
		} catch (IAmError e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> Anime_FindIndexAnime(int limit) {
		return maiKissReo.Anime_FindIndexAnime(limit);
	}

	@Override
	public Map<String, Object> User_FindUserByID(String uid) throws HomeNotFoundException{
		long userID;
		try {
			userID = Long.parseLong(uid);
		} catch (Exception e) {
			throw new HomeNotFoundException();
		}
		Map<String, Object> data = getData(DataType.User, userID);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.User_FindUserByID(uid);
			if (stringUtil.isNull(data)) {
				throw new HomeNotFoundException();
			}else {
				saveData(DataType.User, userID, data);
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> User_FindUserByUsername(String username) throws HomeNotFoundException{
		long uid = new UserID(username).GetlongCode();
		Map<String, Object> data = getData(DataType.User, uid);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.User_FindUserByUsername(username);
			if (stringUtil.isNull(data)) {
				throw new HomeNotFoundException();
			}else {
				saveData(DataType.User, uid, data);
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByID(String uid) throws HomeNotFoundException{
		Long userid;
		try {
			userid = Long.parseLong(uid);
		} catch (Exception e) {
			throw new HomeNotFoundException();
		}
		Map<String, Object> data = getData(DataType.User, userid);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.User_FindUserByShowByID(uid);
			if (stringUtil.isNull(data)) {
				throw new HomeNotFoundException();
			}else {
				saveData(DataType.User, userid, data);
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByUsername(String username) throws HomeNotFoundException{
		long userID = new UserID(username).GetlongCode();
		Map<String, Object> data = getData(DataType.User, userID);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.User_FindUserByShowByUsername(username);
			if (stringUtil.isNull(data)) {
				throw new HomeNotFoundException();
			}else {
				saveData(DataType.User, userID, data);
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByURL(String url) throws HomeNotFoundException{
		Map<String, Object> data = getData(DataType.User, url);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.User_FindUserByShowByURL(url);
			if (stringUtil.isNull(data)) {
				throw new HomeNotFoundException();
			}else {
				saveData(DataType.User, url, data);
			}
		}
		return data;
	}

	@Override
	public void User_AddUser(Users user) {
		maiKissReo.User_AddUser(user);
		Long uid = user.getUid();
		try {
			Map<String, Object> data = maiKissReo.User_FindUserByID(uid.toString());
			if (stringUtil.isNull(data)) {
				throw new IAmError("数据保存失败，原因未知");
			}else {
				saveData(DataType.User, uid.longValue(), data);
			}
		} catch (HomeNotFoundException e) {
		} catch (IAmError e) {
			e.printStackTrace();
		}
	}

	@Override
	public void User_UpdataUser(Users user, String uid) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void User_UpdataPic(String pic, String uid) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_Add(String tag_name) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_UpdataTag(String tagid, String tagname) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void Tag_Delete() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<Map<String, Object>> Tag_FindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> Tag_FindByTagID(String tagid) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> Blog_FindBlogByID(String id) throws BlogNotFoundException {
		long blogid;
		try {
			blogid = Long.parseLong(id);
		} catch (Exception e) {
			throw new BlogNotFoundException();
		}
		Map<String, Object> data = getData(DataType.Blog, blogid);
		if (stringUtil.isNull(data)) {
			data = maiKissReo.Blog_FindBlogByID(id);
			if (stringUtil.isNull(data)) {
				throw new BlogNotFoundException();
			}else {
				saveData(DataType.Blog, blogid, data);
			}
		}
		return data;
	}

	@Override
	@Deprecated
	public Map<String, Object> Blog_FindBlogByUser(String uid) throws BlogNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void Blog_AddBlog(BlogArticle article) {
		maiKissReo.Blog_AddBlog(article);
	}

	public static void MapDBExit() {
		// TODO Auto-generated method stub
		DataSet.MapDBExit();
	}

	@Override
	public void setImgMapDB(String filePath, String OriginalFilename) {
		// TODO Auto-generated method stub
		super.setImgMapDB(filePath, OriginalFilename);
	}

	@Override
	public String getImgMapDB(String filePath) {
		// TODO Auto-generated method stub
		return super.getImgMapDB(filePath);
	}
}
