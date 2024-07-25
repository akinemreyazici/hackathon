import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.works.hackathon.model.ExpenseProduct

class ItemRepository(private val context: Context) {

    fun getDataFromJson(fileName: String): List<ExpenseProduct>? {
        val jsonString = readJsonFromAssets(fileName)
        return jsonString?.let {
            parseJsonToList(it)
        }
    }

    fun getDataFromJsonByCategory(fileName: String, category: String): List<ExpenseProduct>? {
        val jsonString = readJsonFromAssets(fileName)
        return jsonString?.let {
            parseJsonToList(it)?.filter { item -> item.category == category }
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

    private fun parseJsonToList(jsonString: String): List<ExpenseProduct> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<List<ExpenseProduct>>() {}.type)
    }
}

