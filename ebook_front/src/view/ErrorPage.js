import React from "react";

function ErrorPage({ onReload }) {
    return (
        <div>
            <h1>网络错误</h1>
            <p>抱歉，我们无法连接到服务器，请检查您的网络连接并重试。</p>
            <button onClick={onReload}>重新加载</button>
        </div>
    );
}

export default ErrorPage;