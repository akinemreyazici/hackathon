import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.works.hackathon.model.Item

class StoreRepository(private val context: Context) {

    fun getDataFromJson(fileName: String): List<Item>? {
        val jsonString = readJsonFromAssets(fileName)
        return jsonString?.let {
            parseJsonToList(it)
        }
    }

    private fun readJsonFromAssets(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseJsonToList(jsonString: String): List<Item> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<Item>>() {}.type)
    }
}

