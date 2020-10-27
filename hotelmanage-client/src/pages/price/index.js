import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'
import { history, connect } from 'umi'
import { Row, Col, Button, Popconfirm, Form, Input, Space, Card } from 'antd'
import { withI18n } from '@lingui/react'
import { Page } from 'components'
import { stringify } from 'qs'
import List from './components/List'
import Filter from './components/Filter'

@withI18n()
@connect(({ priceList, loading }) => ({ priceList, loading }))
class PriceList extends PureComponent {
  //修改页面把过滤条件与页码等参数记录在路径上重新刷新
  // handleRefresh = newQuery => {
  //   const { location } = this.props
  //   const { query, pathname } = location

  //   history.push({
  //     pathname,
  //     search: stringify(
  //       {
  //         ...query,
  //         ...newQuery,
  //       },
  //       { arrayFormat: 'repeat' }
  //     ),
  //   })
  // }

  //处理删除操作
  // handleDeleteItems = () => {
  //   const { dispatch, teamList } = this.props
  //   const { list, pagination, selectedRowKeys } = teamList

  //   //发送删除操作
  //   dispatch({
  //     type: 'teamList/multiDelete',
  //     payload: {
  //       ids: selectedRowKeys,
  //     },
  //   }).then(() => {
  //     //重新刷新
  //     this.handleRefresh({
  //       pageIndex:
  //         list.length === selectedRowKeys.length && pagination.current > 1
  //           ? pagination.current - 1
  //           : pagination.current,
  //     })
  //   })
  // }

  get listProps() {
    const { dispatch, priceList, loading } = this.props
    const { list } = priceList

    return {
      dataSource: list,
      loading: loading.effects['priceList/query'],
      //pagination,
      onChange: page => {
        // this.handleRefresh({
        //   pageIndex: page.current,
        //   pageSize: page.pageSize,
        // })
      },
      onDeleteItem: id => {
        // 暂时不做任何事
        //   dispatch({
        //     type: 'teamList/delete',
        //     payload: id,
        //   }).then(() => {
        //     this.handleRefresh({
        //       pageIndex:
        //         list.length === 1 && pagination.current > 1
        //           ? pagination.current - 1
        //           : pagination.current,
        //     })
        //   })
      },
      onShowItem(item) {
        // console.log(item)
        // let pathname = '/team/'+item.id
        // history.push({pathname: pathname, state: {edit: false}})
      },
      onEditItem(item) {
        console.log(item)
        //dispatch({type:'teamList/edit', payload:{id: item.id}})
        //let pathname = '/team/new'
        //history.push({pathname: pathname, state: {edit: true}})
      },

      // 表格行可选择，暂不支持
      // rowSelection: {
      //   selectedRowKeys,//初始值
      //   //每次修改的更新操作
      //   onChange: keys => {
      //     dispatch({
      //       type: 'user/updateState',
      //       payload: {
      //         selectedRowKeys: keys,
      //       },
      //     })
      //   },
      // },
    }
  }

  get filterProps() {
    const { dispatch, location, i18n } = this.props
    const { query } = location

    return {
      i18n,
      filter: {
        ...query,
      },
      onFilterChange: values => {
        console.log(values)
        dispatch({
          type: 'priceList/query',
          payload: values,
        })
      },
    }
  }

  render() {

    const { priceList } = this.props;
    const { list = [] } = priceList;

    return (
      <Page inner>
        <Filter {...this.filterProps}></Filter>
        <Row gutter={[18, 6]} style={{background:'#eff1f4'}}>

          {
            list.map((item, index) => {
              return (
                <Col span="24">
                  <Card title={item.hotel.name}>
                    <List dataSource={item.pricesList} />
                  </Card>
                </Col>
              )
            })
          }
        </Row>
      </Page>
    )
  }
}

PriceList.PropTypes = {
  PriceList: PropTypes.object,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
}

export default PriceList
