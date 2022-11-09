// 工具包
import axios from "axios"
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

const Qs = require("querystring");

/**
 * instance封装axios
 */
var instance = axios.create({
    // 本地測試
    baseURL: 'http://localhost:8080',
    // 超时时间
    timeout: 20000
});

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    NProgress.start();//开启
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    NProgress.done();//关闭
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    NProgress.done();//关闭
    // 对响应数据做点什么
    return response;
}, function (error) {
    NProgress.done();//关闭
    // 对响应错误做点什么
    return Promise.reject(error);
});

/**
 * service封装axios
 */
var service = axios.create({
    // 本地測試
    baseURL: 'http://localhost:8080',
    // headers:{
    //     'Content-Type':'application/json;charset=UTF-8'
    // },
    // 超时时间
    timeout: 20000
});

// 添加响应拦截器
service.interceptors.response.use(function (response) {
    NProgress.done();//关闭
    // 对响应数据做点什么
    return response;
}, function (error) {
    NProgress.done();//关闭
    // 对响应错误做点什么
    return Promise.reject(error);
});

// 添加请求拦截器
service.interceptors.request.use(function (config) {
    NProgress.start();//开启
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    NProgress.done();//关闭
    // 对请求错误做些什么
    return Promise.reject(error);
});



let get = function (url, params) {
    return instance.get(url, {params});
}
let post = function (url, params) {
    //form
    return instance.post(url, Qs.stringify(params));
}
let put = function (url, params) {
    return instance.put(url, Qs.stringify(params));
}
let del = function (url, params) {
    return instance.delete(url, {params});
}

// 导出get和post方法
export {
    get, put, del, post
}

export default service

