import api from 'api'
import { history } from 'umi'
const { pathToRegexp } = require("path-to-regexp")

const { queryHotelList } = api

export default {
    namespace: "hotelList",
    subscriptions: {
        //监听路径的变化，跳转之后发起查询操作
        setup({ dispatch, history }) {
            history.listen(location => {
                if (pathToRegexp('/hotel').exec(location.pathname)) {
                    let query = location.query || {}
                    let defaultquery = { pageNum: 1, pageSize: 10}
                    const payload = { ...defaultquery, ...query }

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
    },

    effects: {
        *query({payload}, { call, put }) {
            const { success, data } = yield call(queryHotelList, payload)
            if (success) {
                yield put({ type: 'querySuccess', payload: data })
            } else {
                throw data
            }
        },
        *edit({payload}, { call }) {
            const { success, data } = yield call(queryTeam, payload)
            if (success) {
                history.push({pathname:'/hotel/new', state: data})
            } else {
                throw data
            }
        }
    }
}