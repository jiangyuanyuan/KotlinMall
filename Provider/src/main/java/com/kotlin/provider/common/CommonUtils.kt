import com.kotlin.base.common.BaseConstant
import com.kotlin.base.utils.AppPrefsUtils

fun isLogined():Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}