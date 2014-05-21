var Wakelock = function () {};
/**
 * 设置提示值
 * @param content
 * @param length
 * @returns {*}
 */
Wakelock.prototype.acquireWakeLock = function () {
    return cordova.exec(null, null,"WakelockPlugin","acquireWakeLock",[]);
};

Wakelock.prototype.releaseWakeLock = function () {
    return cordova.exec(null, null,"WakelockPlugin","releaseWakeLock",[]);
};

module.exports = (new Wakelock());
