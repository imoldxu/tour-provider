import { Button, Card, Col, DatePicker, Input, Popover, Row, Select, TimePicker, Form } from 'antd';
import React, { useState, PureComponent } from 'react';
import { connect } from 'umi';
import TableForm from './components/TableForm';
import styles from './style.less';
import { Trans, withI18n } from '@lingui/react'
import PropTypes from 'prop-types'
import { Page } from 'components'
import { cloneDeep } from 'lodash'

const { Option } = Select;
const { RangePicker } = DatePicker;

@withI18n()
@connect(({ newProvider, loading }) => ({ newProvider, loading }))
class NewProvider extends PureComponent {

  formRef = React.createRef()

  handleOk = values => {
    const { dispatch } = this.props

    let data = cloneDeep(values);
    for(const i in data.contact){
      delete data.contact[i].key;
      delete data.contact[i].isNew;
      delete data.contact[i].editable;
    }

    dispatch({ type: 'newProvider/new', payload: data })
  }

  render() {
    const { submitting, location, newProvider } = this.props
    const { state = {} } = location

    return (
      <Page inner>
        <Form
          ref={this.formRef}
          layout="vertical"
          onFinish={this.handleOk}
          initialValues={state}
        >
          {/* <Form.Item name="id" noStyle>
            <Input type='hidden' />
          </Form.Item> */}
          <Form.Item
            label="供应商名称" 
            name="name"
            rules={[{ required: true }]}
            hasFeedback>
              <Input placeholder="请输入供应商名称" />
          </Form.Item>
             
          <Form.Item
            name="contact"
            label="联系人"
          >
            <TableForm />
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

NewProvider.PropTypes = {
  location: PropTypes.object,
  dispatch: PropTypes.func,
  loading: PropTypes.object,
}


export default NewProvider