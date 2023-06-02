const oomeUtils = {}

oomeUtils.copyToClipboard = (text) => {
    if (!navigator.clipboard) {
        const textArea = document.createElement('textarea');
        textArea.value = text;
        document.body.appendChild(textArea);
        textArea.select();
        document.execCommand('copy');
        document.body.removeChild(textArea);
        return;
    }
    navigator.clipboard.writeText(text)
        .then(() => {
            console.log('클립보드로 복사되었습니다.');
        })
        .catch(err => {
            console.error('클립보드 복사에 실패하였습니다:', err);
        });
}


export default oomeUtils;