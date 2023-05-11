package com.wx.entity;

public class Choices {
    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;

    public void setText(String text) {
        this.text = text;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Choices))
            return false;
        Choices other = (Choices)o;
        if (!other.canEqual(this))
            return false;
        if (getIndex() != other.getIndex())
            return false;
        Object this$text = getText(), other$text = other.getText();
        if ((this$text == null) ? (other$text != null) : !this$text.equals(other$text))
            return false;
        Object this$logprobs = getLogprobs(), other$logprobs = other.getLogprobs();
        if ((this$logprobs == null) ? (other$logprobs != null) : !this$logprobs.equals(other$logprobs))
            return false;
        Object this$finish_reason = getFinish_reason(), other$finish_reason = other.getFinish_reason();
        return !((this$finish_reason == null) ? (other$finish_reason != null) : !this$finish_reason.equals(other$finish_reason));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Choices;
    }

//    public int hashCode() {
//        int PRIME = 59;
//        result = 1;
//        result = result * 59 + getIndex();
//        Object $text = getText();
//        result = result * 59 + (($text == null) ? 43 : $text.hashCode());
//        Object $logprobs = getLogprobs();
//        result = result * 59 + (($logprobs == null) ? 43 : $logprobs.hashCode());
//        Object $finish_reason = getFinish_reason();
//        return result * 59 + (($finish_reason == null) ? 43 : $finish_reason.hashCode());
//    }

    public String toString() {
        return "Choices(text=" + getText() + ", index=" + getIndex() + ", logprobs=" + getLogprobs() + ", finish_reason=" + getFinish_reason() + ")";
    }

    public String getText() {
        return this.text;
    }

    public int getIndex() {
        return this.index;
    }

    public String getLogprobs() {
        return this.logprobs;
    }

    public String getFinish_reason() {
        return this.finish_reason;
    }
}
