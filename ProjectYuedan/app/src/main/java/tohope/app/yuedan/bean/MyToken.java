package tohope.app.yuedan.bean;

import java.util.List;

/**
 * Created by ChenZhihong on 2017/3/31.
 */

public class MyToken {


    private List<ResultBean> Result;
    private List<TokenBean> Token;

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public List<TokenBean> getToken() {
        return Token;
    }

    public void setToken(List<TokenBean> Token) {
        this.Token = Token;
    }

    public static class ResultBean {
        /**
         * State : ok
         * Message : API接口调用成功
         */

        private String State;
        private String Message;

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }
    }

    public static class TokenBean {
        /**
         * token : b5a1adee30f0dedbfce1452cbd837a3f
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
