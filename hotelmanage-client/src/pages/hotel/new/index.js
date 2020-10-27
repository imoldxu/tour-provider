import { Button, Card, Col, DatePicker, Input, Popover, Row, Select, Checkbox, Form } from 'antd';
import React, { useState, PureComponent } from 'react';
import { connect } from 'umi';
import TableForm from './components/TableForm';
import SelectModal from './components/SelectModal';
import styles from './style.less';
import { Trans, withI18n } from '@lingui/react'
import PropTypes from 'prop-types'
import { Page } from 'components'

const { Option } = Select;
const { RangePicker } = DatePicker;

const layout = {
  //labelCol: { span: 8 },
  //wrapperCol: { span: 16 },
};
const tailLayout = {
  //wrapperCol: { offset: 8, span: 16 },
};

const areaOptions = ["春熙路","宽窄巷子","杜甫草堂","文殊院","武侯祠","一环内","二环内","三环内"]

@withI18n()
@connect(({ NewHotel, loading }) => ({ NewHotel, loading }))
class NewHotel extends PureComponent {
  formRef = React.createRef()

  handleOk = values => {
    const { dispatch } = this.props
    console.log(values)
    dispatch({ type: 'newHotel/new', payload: values })
  }

  render() {
    const { submitting, location, newHotel } = this.props
    const { state = {city:"成都", xclv:"3钻", lv:"准3", canForeign:"false"} } = location

    return (
      <Page inner>
          <Form
            {...layout}
            ref={this.formRef}
            name="newHotel"
            layout="vertical"
            onFinish={this.handleOk}
            initialValues={state}
          >
            {/* <Form.Item name="id" noStyle>
              <Input type='hidden' />
            </Form.Item> */}
            
            <Form.Item {...tailLayout}
              label="酒店名称" 
              name="name"
              rules={[{ required: true }]}
              hasFeedback>
                <Input placeholder="请输入酒店名称" />
            </Form.Item>
          
            <Form.Item {...tailLayout}
              label="城市"
              name="city"
              rules={[{ required: true }]}
              hasFeedback
            >
              <Select>
                <Option value="成都">成都</Option>
                <Option value="九寨沟">九寨沟</Option>
                <Option value="峨眉">峨眉</Option>
              </Select>
            </Form.Item>
          
            <Form.Item {...tailLayout}
              label="地址"
              name="address"
              rules={[{ required: true }]}
              hasFeedback
            >
              <Input placeholder="请输入地址">
              </Input>
            </Form.Item>
            
            <Form.Item {...tailLayout}
              label="携程等级"
              name="xclv"
              rules={[{required: true}]}
              hasFeedback
            >
              <Select>
                <Option value="2钻">2钻</Option>
                <Option value="3钻">3钻</Option>
                <Option value="4钻">4钻</Option>
                <Option value="5钻">5钻</Option>
              </Select>
            </Form.Item>
            <Form.Item {...tailLayout}
              label="标准等级"
              name="lv"
              rules={[{ required: true}]}
              hasFeedback
            >
              <Select>
                  <Option value="准3">准3</Option>
                  <Option value="挂3">挂3</Option>
                  <Option value="准4">准4</Option>
                  <Option value="挂4">挂4</Option>
                  <Option value="准5">准5</Option>
                  <Option value="挂5">挂5</Option>
                </Select>
            </Form.Item>
            <Form.Item {...tailLayout}
              label="是否涉外"
              name="canForeign"
              rules={[{ required: true }]}
              hasFeedback
            >
              <Select>
                <Option value="true">是</Option>
                <Option value="false">否</Option>
              </Select>
            </Form.Item>
            <Form.Item {...tailLayout}
              label="区域商圈"
              name="area"
            >
              <Checkbox.Group options={areaOptions}/>
            </Form.Item>

            <Row justify="center">
              <Col>
                <Button type="primary" htmlType="submit" loading={submitting}>
                  提交
              </Button>
              </Col>
            </Row>

          </Form>
      </Page>
    )
  }
}

NewHotel.PropTypes = {
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
}


export default NewHotel