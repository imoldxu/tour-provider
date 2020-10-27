import api from 'api'
import { history } from 'umi'
const { pathToRegexp } = require("path-to-regexp")

const { queryPriceList } = api

export default {
    namespace: "priceList",
    subscriptions: {
        //监听路径的变化，跳转之后发起查询操作
        // setup({ dispatch, history }) {
        //     history.listen(location => {
        //         if (pathToRegexp('/price').exec(location.pathname)) {
        //             let query = location.query || {}
        //             let defaultquery = { pageIndex: 1, pageSize: 10}
        //             const payload = { ...defaultquery, ...query }

        //             dispatch({
        //                 type: 'query',
        //                 payload,
        //             })
        //         }
        //     })
        // },
    },

    reducers: {
        //处理查询结果，合并到state中
        querySuccess(state, { payload }) {
            return {
                ...state,
                ...payload,
            }
        },
    },

    effects: {
        *query({payload}, { call, put }) {
            const { success, data } = yield call(queryPriceList, payload)
            if (success) {
                if (data.length==0){
                    message.success("没有匹配的酒店价格", 5, function(){})
                }

                yield put({ type: 'querySuccess', payload: {list:data} })
            } else {
                throw data
            }
        },
    }
}