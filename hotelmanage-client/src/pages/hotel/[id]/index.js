import PropTypes from 'prop-types'
import React, { PureComponent } from 'react'
import { connect } from 'umi'
import { Card, Descriptions } from 'antd'
import styles from './style.less';
import TouristList from './components/TouristList'
import { Page } from 'components'

@connect(({ teamDetail, loading }) => {console.log(teamDetail); return ({ teamDetail, loading })})
class TeamDetail extends PureComponent {

    render() {

        const { teamDetail } = this.props

        return (
            <Page inner>
                <Card title="团队信息" className={styles.card} bordered={false}>
                    <Descriptions
                        title="团队信息"
                        style={{
                        marginBottom: 32,
                        }}
                    >
                        <Descriptions.Item label="团队编号">{teamDetail.id}</Descriptions.Item>
                        <Descriptions.Item label="线路名称">{teamDetail.name}</Descriptions.Item>
                        <Descriptions.Item label="出发日期">{teamDetail.startDate}</Descriptions.Item>
                        <Descriptions.Item label="结束日期">{teamDetail.endDate}</Descriptions.Item>
                    </Descriptions>
                    <Descriptions
                        title="导游信息"
                        style={{
                        marginBottom: 32,
                        }}
                    >
                        <Descriptions.Item label="导游证号">{teamDetail.guide.idNo}</Descriptions.Item>
                        <Descriptions.Item label="导游名称">{teamDetail.guide.name}</Descriptions.Item>
                        <Descriptions.Item label="联系电话">{teamDetail.guide.phone}</Descriptions.Item>
                    </Descriptions>
                    <Descriptions
                        title="司机信息"
                        style={{
                        marginBottom: 32,
                        }}
                    >
                        <Descriptions.Item label="司机证号">{teamDetail.driver.idNo}</Descriptions.Item>
                        <Descriptions.Item label="司机名称">{teamDetail.driver.name}</Descriptions.Item>
                        <Descriptions.Item label="联系电话">{teamDetail.driver.phone}</Descriptions.Item>
                    </Descriptions>
                    <Descriptions
                        title="车辆信息"
                        style={{
                        marginBottom: 32,
                        }}
                    >
                        <Descriptions.Item label="车型">{teamDetail.carInfo}</Descriptions.Item>
                        <Descriptions.Item label="车牌号">{teamDetail.carNo}</Descriptions.Item>
                    </Descriptions>
                </Card>
                <Card title="游客" bordered={false}>
                    <TouristList tourist={teamDetail.tourist}/>
                </Card>

            </Page>
        )
    }

}

TeamDetail.propTypes = {
    teamDetail: PropTypes.object,
}

export default TeamDetail