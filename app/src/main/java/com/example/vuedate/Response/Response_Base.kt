import com.google.gson.annotations.SerializedName

data class Response_Base (

	@SerializedName("page") val page : Page
)

data class Page (

	@SerializedName("title") val title : String,
	@SerializedName("total-content-items") val total_content_items : Int,
	@SerializedName("page-num") val pagenum : Int,
	@SerializedName("page-size") val pagesize : Int,
	@SerializedName("content-items") val contentitems : Content_items
)

data class Content (

	@SerializedName("name") val name : String,
	@SerializedName("poster-image") val posterimage : String
)
data class Content_items(

@SerializedName("content") val content : List<Content>
)