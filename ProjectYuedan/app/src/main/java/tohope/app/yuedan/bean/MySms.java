package tohope.app.yuedan.bean;

import java.util.List;

/**
 * 逸动无线
 * Created by ChenZhihong on 2017/4/12.
 */

public class MySms {

    private List<ResultBean> Result;
    private List<VerificationCodeBean> VerificationCode;

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public List<VerificationCodeBean> getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(List<VerificationCodeBean> VerificationCode) {
        this.VerificationCode = VerificationCode;
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

    public static class VerificationCodeBean {
        /**
         * vcode : 673706
         */

        private String vcode;

        public String getVcode() {
            return vcode;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }
    }
}
