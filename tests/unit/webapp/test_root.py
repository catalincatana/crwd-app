
def test_root_path(app, client):
    res = client.get('/')
    assert res.status_code == 200
    expected = "Glad to see you again-fail"
    assert expected == res.get_data(as_text=True)