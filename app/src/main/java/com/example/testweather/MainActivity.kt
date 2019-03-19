package com.example.testweather

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.testweather.adapter.WeatherAdapter
import com.example.testweather.interfaces.IMainActivity
import com.example.testweather.interfaces.IMainPresenter
import com.example.testweather.model.WeatherDate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    IMainActivity {

    @Inject
    lateinit var presenter: IMainPresenter

    private var weatherAdapter = WeatherAdapter()

    private var currentCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as App).getAppComponent()?.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.loadWeather()
        presenter.setUpView(this)

        rvWeather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvWeather.adapter = weatherAdapter

        swMainSwipe.setOnRefreshListener {
            currentCity = presenter.getCodeCity()
            presenter.loadWeather(currentCity)
        }

        bRefresh.setOnClickListener {
            currentCity = presenter.getCodeCity()
            presenter.loadWeather(currentCity)
        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun showWeathers(listWeather: List<WeatherDate>) {

        weatherAdapter.mData = listWeather
        swMainSwipe.isRefreshing = false
        llNoData.visibility = if (rvWeather.adapter?.itemCount == 0) View.VISIBLE else View.GONE
        rvWeather.visibility = if (llNoData.visibility == View.VISIBLE) View.GONE else View.VISIBLE

    }

    override fun showError(error: String?) {

        swMainSwipe.isRefreshing = false
        llNoData.visibility = View.VISIBLE
        rvWeather.visibility = if (llNoData.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        Toast.makeText(this, error ?: getString(R.string.toast_unknown_error),
            Toast.LENGTH_SHORT).show()

    }

    override fun upDateActionBar(nameCity: Int) {
        supportActionBar?.title = resources.getString(nameCity)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        rvWeather.visibility = View.VISIBLE

        when (item.itemId) {

            R.id.moscow -> {
                presenter.loadWeather(CityCode.MOSCOW.code.toString())
                presenter.setTitleForCity(CityCode.MOSCOW.code)

            }

            R.id.saint_petersburg -> {
                presenter.loadWeather(CityCode.SAINT_PETERSBURG.code.toString())
                presenter.setTitleForCity(CityCode.SAINT_PETERSBURG.code)
            }

            R.id.kazan -> {
                presenter.loadWeather(CityCode.KAZAN.code.toString())
                presenter.setTitleForCity(CityCode.KAZAN.code)
            }

            R.id.irkutsk -> {
                presenter.loadWeather(CityCode.IRKUTSK.code.toString())
                presenter.setTitleForCity(CityCode.IRKUTSK.code)
            }

            R.id.krasnoyarsk -> {
                presenter.loadWeather(CityCode.KRASNOYARSK.code.toString())
                presenter.setTitleForCity(CityCode.KRASNOYARSK.code)
            }

            R.id.novosibirsk -> {
                presenter.loadWeather(CityCode.NOVOSIBIRSK.code.toString())
                presenter.setTitleForCity(CityCode.NOVOSIBIRSK.code)
            }

            R.id.perm -> {
                presenter.loadWeather(CityCode.PERM.code.toString())
                presenter.setTitleForCity(CityCode.PERM.code)
            }

            R.id.pskov -> {
                presenter.loadWeather(CityCode.PSKOV.code.toString())
                presenter.setTitleForCity(CityCode.PSKOV.code)
            }

            R.id.samara -> {
                presenter.loadWeather(CityCode.SAMARA.code.toString())
                presenter.setTitleForCity(CityCode.SAMARA.code)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }

}