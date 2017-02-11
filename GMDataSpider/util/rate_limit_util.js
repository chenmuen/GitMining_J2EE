/**
 * Created by chenm on 2016/5/30.
 */

function RateLimitUtil() {
    this.max = 6;
    this.current = 0;
    this.isChanged = false;
    this.last = this.max;
}

RateLimitUtil.prototype.changeAccount = function () {
    // console.log(this.isChanged)
    // console.log(this.last);
    
    var util = this;

    if (this.isChanged) {
        console.log("no change");
        return true;
    }

    if (this.last != this.max) {
        if((this.current+1) % this.max == this.last){
            return false;
        }
    } else {
        this.last = this.current;
        setTimeout(function () {
            util.last = util.max;
        }, 50000);
    }

    this.current = (this.current + 1) % this.max;
    console.log("Change!!!");
    this.isChanged = true;
    setTimeout(function () {
        util.isChanged = false;
    }, 5000);

    return true;

};

module.exports = new RateLimitUtil();