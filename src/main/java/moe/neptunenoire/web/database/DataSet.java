package moe.neptunenoire.web.database;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import moe.neptunenoire.web.mysql.MaiKissReo;

/**
 *
 * @author M
 *
 */
@Component
public class DataSet {

	private static RedisTemplate<String, Map<String, Object>> redis;
	// https://www.cnblogs.com/nfcm/p/7833032.html
	private static MaiKissReo maiKissReo;

	public static final String Anime = "Anime";
	public static final String User = "User";

	/**
	 * 保存用户数据
	 * @param data
	 */
	public static void saveUsersData(Map<String, Object> data) {
		redis.opsForList().set(User, (long)(data.get("uid")), data);
	}


	/**
	 *
	 * @param filter
	 * @return
	 */
	public static Map<String, Object> getUser(long UID){
		return redis.opsForList().index(User, UID);
	}

	/**
	 * 得到用户数
	 * @return
	 */
	public static long getUserNum() {
		return redis.opsForList().size(User);
	}

	/**
	 * 得到数据的数量
	 * @return
	 */
	public static long getAnimeNum() {
		return redis.opsForList().size(Anime);
	}

	/**
	 * 保存一个动画数据
	 * @param data 动画的数据
	 */
	public static void saveAnimeData(Map<String, Object> data) {
		redis.opsForList().set(Anime, (int)(data.get("anime_id")), data);
	}

	/**
	 * 得到动画集合
	 * @param filter 过滤规则
	 * @return
	 */
	public static List<Map<String, Object>> getAllAnimeData(Predicate<? super Map<String, Object>> filter){
		return redis.opsForList().range(Anime, 0, 5000).stream().filter(filter).collect(Collectors.toList());
	}

	/**
	 * 得到一部动画
	 * @param filter 过滤规则
	 * @return
	 */
	public static Map<String, Object> getAnimeOne(int AnimeID){
		return redis.opsForList().index(Anime, AnimeID);
	}

	/**
	 * 数据操作类初始化
	 * @param maiKissReo
	 */
	@Autowired
	public DataSet(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		initData(maiKissReo, redis);
	}

	/**
	 * 数据初始化
	 * @param maiKissReo
	 */
	private void initData(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		DataSet.maiKissReo = maiKissReo;
		DataSet.redis = redis;
	}
}
