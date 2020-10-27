import api from 'api'
import { history } from 'umi'
const { pathToRegexp } = require("path-to-regexp")

const { queryProviderList, uploadPrices } = api

export default {
    namespace: "providerList",
    state:{
        modalVisible: false, 
        selectProvider: "",
    },
    subscriptions: {
        //监听路径的变化，跳转之后发起查询操作
        setup({ dispatch, history }) {
            history.listen(location => {
                if (pathToRegexp('/provider').exec(location.pathname)) {
                    let query = location.query || {}
                    //let defaultquery = { pageIndex: 1, pageSize: 10}
                    const payload = { ...query }

                    dispatch({
                        type: 'query',
                        payload,
                    })
                }
            })
        },
    },

    reducers: {
        //处理查询结果，合并到state中
        querySuccess(state, { payload }) {
            return {
                ...state,
                ...payload,
            }
        },
        showModal(state, {payload}) {
            return {
                ...state,
                modalVisible: true,
                ...payload,
            }
        },
        closeModal(state, {payload}){
            return {
                ...state,
                modalVisible: false,
            }
        }
    },

    effects: {
        *query({payload}, { call, put }) {
            const { success, data } = yield call(queryProviderList, payload)
            if (success) {
                yield put({ type: 'querySuccess', payload: {list:data} })
            } else {
                throw data
            }
        },
        *edit({payload}, { call }) {
            // const { success, data } = yield call(queryTeam, payload)
            // if (success) {
            //     history.push({pathname:'/provider/new', state: data})
            // } else {
            //     throw data
            // }
        },
        *uploadPrice({payload}, {call, put}) {
            const rs = yield call(uploadPrices, payload);

            if (rs.success) {
                //关闭modal
                yield put({ type: 'closeModal', payload: {} })

                //接收到后端的数据流以blob的方式创建一个a标签自动触发下载
                const blob = new Blob([rs.data]) //, { type: 'text/plain' })
                const fileName = "uploadFailed.xlsx";
                if ('download' in document.createElement('a')) { // 非IE下载
                    const elink = document.createElement('a');
                    elink.download = fileName;
                    elink.style.display = 'none';
                    elink.href = URL.createObjectURL(blob);
                    document.body.appendChild(elink);
                    elink.click();
                    URL.revokeObjectURL(elink.href);// 释放 URL对象
                    document.body.removeChild(elink);
                } else { // IE10+下载
                    navigator.msSaveBlob(blob, fileName)
                }
                //notification.success("数据导出成功")

            } else {
                console.log("1111");
                throw data
            }
        }
    }
}