
package ratnatech.farmerapp.ModelClass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResponseModel implements Parcelable
{

    @SerializedName("status")//success
    private boolean status ;//success
    @SerializedName("message")
    private String message;

    public final static Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ResponseModel createFromParcel(Parcel in) {
            ResponseModel instance = new ResponseModel();
            instance.status = ((boolean) in.readValue((boolean.class.getClassLoader())));//success
           instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ResponseModel[] newArray(int size) {
            return (new ResponseModel[size]);
        }

    };

    /**
     * 
     * @return
     *     The success
     */
    public boolean isSuccess() {
        return status;
        //success
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message; //

    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);//success
        dest.writeValue(message);//
    }

    public int describeContents() {
        return  0;
    }

}
