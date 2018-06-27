package moe.neptunenoire.web.database;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.controller.error.HomeNotFoundException;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.util.StringUtil;

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
				saveData(DataType.Anime, data);
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
		}
		if (stringUtil.isNull(data)) {
			throw new HomeNotFoundException();
		}
		return data;
	}

	@Override
	public Map<String, Object> User_FindUserByUsername(String username) throws HomeNotFoundException{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByID(String uid) throws HomeNotFoundException{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByUsername(String username) throws HomeNotFoundException{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> User_FindUserByShowByURL(String url) throws HomeNotFoundException{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void User_AddUser(Users user) {
		// TODO 自動生成されたメソッド・スタブ

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



}
