"use strict";

// Class Definition
const Base = function() {
    
    const active = 'active';
    const $body = $('body');

    /**
     * Page loader
     *--------------------------------------------------------------*/
    const loader = () => {
        $('#loader').fadeOut(1000);
    }

    /**
     * Sidebar toggler
     *--------------------------------------------------------------*/
    const sidebar = () => {
        let toggled = false;

        $('.sidebar-toggler').on('click', function() {
            toggled = !toggled;

            $(this).toggleClass(active);
            $body.attr('data-sidebar-toggle', toggled ? 'true' : null);
        });
    }

// 호출하여 초기화
sidebar();


    /**
     * Toggle search results
     *--------------------------------------------------------------*/
    const search = () => {
        $('#search_input').on('click', function(e) {
            e.stopPropagation();
            const dropdownList = document.querySelectorAll('[data-bs-toggle="dropdown"]');
            dropdownList.forEach(item => {
                const dropdown =  bootstrap.Dropdown.getInstance(item);
                dropdown && dropdown.hide();
            });
            
            $body.attr('data-search-results', 'true');
        });

        $('.search').on('click', function(e) {
            e.stopPropagation();
        });

        $body.on('click', function() {
            $(this).removeAttr('data-search-results');
        });
    }

    /**
     * Initialize theme settings
     * Plugin: Settings
     *--------------------------------------------------------------*/
    const initSettings = () => {
        // If you want to remove settings from the theme, remove or comment below line.
        $body.settings();
    }

    /**
     * Initialize scrollbars
     * Plugin: Perfect scrollbar [https://perfectscrollbar.com/]
     *--------------------------------------------------------------*/
    const initScroll = () => {
        $('[data-scroll="true"]').each(function() {
            // Bind perfect scrollbar with element.
            new PerfectScrollbar(this, {
                wheelSpeed: 2,
                swipeEasing: true,
                wheelPropagation: false,
                minScrollbarLength: 40
            });
        });
    }

    /**
     * Initialize swiper slider
     * Plugin: Swiper [https://swiperjs.com/]
     *--------------------------------------------------------------*/
    const initSwiper = () => {
        const desktopSpace = 24;
        const mobileSpace = 16;

        $('.swiper').each(function() {
            const slides = parseInt(this.getAttribute('data-swiper-slides'), 10);
            const carousel = this.parentElement;
            const loop = this.getAttribute('data-swiper-loop');
            const autoplay = this.getAttribute('data-swiper-autoplay');
            const grid = this.getAttribute('data-swiper-grid');
            const next = carousel.querySelector('.swiper-button-next');
            const prev = carousel.querySelector('.swiper-button-prev');
            const pagination = carousel.querySelector('.swiper-pagination');
            const paginationType = this.getAttribute('data-swiper-pagination') ? this.getAttribute('data-swiper-pagination') : 'bullets';
            const scrollbar = carousel.querySelector('.swiper-scrollbar');
            const data = this.querySelectorAll('.swiper-slide');
            const rows = Array.from(data).reduce((all, one, i) => {
                const ch = Math.floor(i/slides); 
                all[ch] = [].concat((all[ch]||[]),one); 
                return all;
            }, []).length;
             
            // Set responsive slide
            let desktop = slides;
            let tablet = 1;
            let mobile = 2;

            if (slides === 1) {
                tablet = mobile = 1;

            } else {
                if (slides > 1 && slides < 5) {
                    tablet = 2;
                    mobile = 1;
                } else if (slides >= 5) {
                    tablet = 3;
                    mobile = 2;
                }
            }

            // Swiper options
            const options = {
                speed: 500,
                slidesPerView: mobile,
                slidesPerGroup: 1,
                spaceBetween: mobileSpace,

                a11y: true,
                rtl: Utils.isRTL(),

                breakpoints: {
                    576: {
                        slidesPerView: tablet
                    },
                    1200: {
                        slidesPerView: desktop,
                        spaceBetween: desktopSpace
                    }
                }
            }

            if (loop) {
                options.loop = loop;
            }

            if (autoplay) {
                options.autoplay = {
                    delay: 5000,
                    disableOnInteraction: false,
                    pauseOnMouseEnter: true
                };
            }

            if (grid) {
                options.breakpoints[1200].grid = {
                    rows: rows,
                    fill: 'row'
                }
            }

            // Add next & prev controls in swiper option if element is exist.
            if (next && prev) {
                options.navigation = {
                    nextEl: next,
                    prevEl: prev,
                }
            }

            // Add pagination control in swiper option if element is exist.
            if (pagination) {
                options.pagination = {
                    el: pagination,
                    type: paginationType,
                    clickable: true,
                    dynamicBullets: true
                }
            }

            // Add scrollbar control in swiper option if element is exist.
            if (scrollbar) {
                options.scrollbar = {
                    el: scrollbar,
                    draggable: true
                }
            }

            // Bind swiper slider with element.
            new Swiper(this, options);

        });
    }

    /**
     * Initialize dropzone file upload
     * Plugin: Dropzone [https://www.dropzone.dev/js/]
     *--------------------------------------------------------------*/
    const initDropzone = () => {
        const fileUpload = document.querySelector('.dropzone');

        if (fileUpload) {
            Dropzone.autoDiscover = false;
            new Dropzone('.dropzone', { url: "/file/post" });
        }
    }

    /**
     * Bootstrap tab to function link material tab
     *--------------------------------------------------------------*/
    const materialTab = () => {
        const matTabs = 'mat-tabs';
        const matLine = 'mat-tabs__line';

        const $matLine = $('<span>', { class: matLine });
        const $matTabs = $('.' + matTabs);
        const $navLink = $('.nav-link');

        // Set mat line on active nav link
        $matTabs.each(function() {
            const width = $(this).find('.nav-link.active').outerWidth();
            const {left} = $(this).find('.nav-link.active').position();
            $matLine.stop().css({ 
                left: left,
                width: width 
            });
            $matLine.appendTo(this);
        });

        // Nav link click
        $navLink.on('click', function() {
            const $this = $(this);
            const $line = $this.closest('.' + matTabs).find('.' + matLine);

            $line.stop().css({
                left: $this.position().left,
                width: $this.outerWidth()
            });
        });
    }

    /**
     * Handle browser back & forward event to change page view
     *--------------------------------------------------------------*/
    const pageChange = () => {
        $(window).on('popstate', function() {
            const href = window.location.href;
            const url = href.split('/').pop();
            if (url) {
                changePageView(url);
            }
        });
    }

    /**
     * Prevent page loading with AJAX 
     *--------------------------------------------------------------*/
    const routing = () => {
        const link = 'a:not([href^="#"])a:not([href^="mail"])a:not([href^="tel"]):not([href^="javascript"]):not(.external):not([target])';

        $(document).on('click', link, function(e) {
            e.preventDefault();
            e.stopPropagation();

            const sidebar = $(this).closest('#sidebar');
            const url = $(this).attr('href') !== 'undefined' ? $(this).attr('href') : null;
            if (url) {
                const history = window.history;
                history.pushState("", "", url);

                changePageView(url, sidebar);
            }
        });
    }

    /**
     * Change page view
     * @param {string} url 
     * @param {Object} sidebar 
     *--------------------------------------------------------------*/
    const changePageView = function(url, sidebar) {
        const $lineLoader = $('#line_loader');
        $lineLoader.show().animate({
            width: 20 + 80 * Math.random() + "%"
        }, 200);

        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'html'
        }).done(function(html, status, response) {
            if (status === 'success' && response.status == 200) {
                html = $('<div>' + html + '</div>');
                $('head title').html(html.find('title').html());
                $('#wrapper').html(html.find('#wrapper').html());
                $('html, body').animate({
                    scrollTop: 0
                }, 100);

                // Initialize click events
                reInit();
                // Change skin of the components on route change.
                changeComponentSkin();
                // Change classes for RTL direction
                convertRTLClassnames();

                // Initialize Dashboard chart
                Dashboard.init();
            }
        }).fail(function() {
            window.location.href = '404.html';
        }).always(function() {
            if ((sidebar && sidebar.length) && $(window).width() < 992) {
                $('.sidebar-toggler').toggleClass(active);
                $body.removeAttr('data-sidebar-toggle');
            }

            $lineLoader.animate({ width: "100%" }, 200).fadeOut(300, function() {
                $(this).width("0");
            });
        });
    }

    /**
     * Change component default skin
     *--------------------------------------------------------------*/
    const changeComponentSkin = () => {
        const skin = Utils.getLocalItem('skin');
        const header = document.getElementById('header');
        const sidebar = document.getElementById('sidebar');

        if (skin && header && sidebar) {
            header.setAttribute('data-header', skin.header);
            sidebar.setAttribute('data-sidebar', skin.sidebar);
        }
    }

    /**
     * Replaces class names on elements that match the specified prefix.
     * @param {jQuery} el - jQuery object containing elements to modify.
     * @param {string} prefix - The class name prefix to replace.
     * @param {string} replace - The new prefix to substitute.
     *--------------------------------------------------------------*/
    const replaceClassName = (el, prefix, replace) => {
        el.each(function() {
            const classNames = $(this).attr("class").split(' ');
            const nextClassNames = classNames.map(function(className) {
                return (
                        className.startsWith(prefix) || 
                        className.endsWith('start') || 
                        className.endsWith('end')
                    ) 
                    ? className.replace(prefix, replace) 
                    : className;
            });
            $(this).attr("class", nextClassNames.join(' '));
        });
    }
    
    /**
     * Converts class names based on the current text direction.
     *--------------------------------------------------------------*/
    const convertRTLClassnames = () => {
        const el = (name) => $(`[class^="${name}"], [class*=" ${name}"], [class$="${name}"],[class*="${name} "]`);
        const reversePrefix = (prefix) => prefix.endsWith('e') ? prefix.replace('e', 's') : prefix.replace('s', 'e');
        const reverseSuffix = (suffix) => suffix.endsWith('start') ? suffix.replace('start', 'end') : suffix.replace('end', 'start');

        if (Utils.isRTL()) {
            const classNames = ['me', 'ms', 'pe', 'ps', 'start', 'end'];
            let elements = classNames.map(className => el(className));
            elements = elements.map(element => element.filter((index, item) => {
                // Check if element has class 'ps' or class starting with 'ps__'
                if (item && item.classList) {
                    const classList = Array.from(item.classList);
                    return !classList.includes('ps') && !classList.some(cls => cls.startsWith('ps__'));
                }

                return item;
            }));
            
            elements.forEach((item, index) => {
                const className = classNames[index];
                const replaceWith = 
                    (className.endsWith('start') || className.endsWith('end')) 
                    ? reverseSuffix(className) 
                    : reversePrefix(className);
                    
                replaceClassName(item, className, replaceWith);
            });
        }
    }

    /**
     * Reinitialize plugins and event on route change
     *--------------------------------------------------------------*/
    const reInit = () => {
        initScroll();
        initSwiper();
        initDropzone();
        materialTab();
        sidebar();
        search();

        if ($('.amplitude-play-pause').hasClass('amplitude-playing')) {
            const song = Amplitude.getActiveSongMetadata();
            $('[data-play-id]').removeClass(active);
            $('[data-play-id=' + song.id + ']').addClass(active);
        }
    }

    return {
        init() {
            loader();
            initSettings();
            reInit();
            pageChange();
            routing();
            convertRTLClassnames();
        }
    }

}();

// Class initialization on page load
$(document).ready(function () {
    Base.init();
});

// Class Definition
const ChartJs = function() {

    return {

        /**
         * Override chart defaults object
         *--------------------------------------------------------------*/
        overrideDefaults() {
            // Chart defaults
            const defaults = Chart.defaults;

            // Chart defaults config settings
            const config = {
                color: Utils.isDarkMode() ? '#92929F' : Utils.getCSSVarValue('body-color'),
                borderColor: Utils.isDarkMode() ? '#34343e' : '#EFF2F5',

                // Chart typo
                font: {
                    family: Utils.getCSSVarValue('body-font-family'),
                    size: 13
                },

                // Chart hover settings
                hover: {
                    intersect: false,
                    mode: 'index'
                }
            };

            // Chart tooltip settings
            const tooltip = {
                titleMarginBottom: 6,
                caretSize: 6,
                caretPadding: 10,
                boxWidth: 8,
                boxHeight: 8,
                boxPadding: 4,
                intersect: false,
                mode: 'index',
                rtl: Utils.isRTL(),
                backgroundColor: '#131416',
                usePointStyle: true,

                padding: {
                    top: 8,
                    right: 12,
                    bottom: 8,
                    left: 12
                }
            }

            // Override Chart js defaults object
            Object.assign(defaults, config);
            Object.assign(defaults.plugins.tooltip, tooltip);
        }
    }

}();

// Call init function when DOM is ready
$(() => { ChartJs.overrideDefaults(); });
"use strict";

// Class Definition
const Dashboard = function() {

    let totalUsersChart, 
        totalSongsChart,
        purchasesChart,
        statisticsChart;

    /**
     * User chart
     * Plugin: Chart.js [https://www.chartjs.org/]
     *--------------------------------------------------------------*/
    const totalUsers = () => {
        const canvas = document.getElementById('total_user');
        
        if (totalUsersChart) {
            totalUsersChart.destroy();
        }
        
        if (canvas) {
            const config = {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                    datasets: [
                        {
                            label: 'Users',
                            data: [65, 59, 42, 73, 56, 55, 40],
                            backgroundColor: Utils.getCSSVarValue('red'),
                            borderColor: Utils.getCSSVarValue('red'),
                            borderWidth: 2,
                            pointRadius: 0,
                            pointHoverRadius: 5,
                            pointHoverBorderWidth: 12,
                            pointBackgroundColor: Chart.helpers.color(Utils.getCSSVarValue('red')).alpha(0).rgbString(),
                            pointBorderColor: Chart.helpers.color(Utils.getCSSVarValue('red')).alpha(0).rgbString(),
                            pointHoverBackgroundColor: Utils.getCSSVarValue('red'),
                            pointHoverBorderColor: Chart.helpers.color(Utils.getCSSVarValue('red')).alpha(0.1).rgbString(),
                        }
                    ]
                },
                options: {
                    title: {
                        display: false,
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                    elements: {
                        borderJoinStyle: 'bevel',
                        borderCapStyle: 'round',
                        line: {
                            tension: 0.4
                        }
                    },
                    scales: {
                        x: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false
                        },
                        y: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false,
                            min: 0,
                            max: 85
                        }
                    },
                    layout: {
                        margin: 0,
                        padding: 0
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                    }
                }
            };
            
            totalUsersChart = new Chart(canvas, config);
        }
    }

    /**
     * User chart
     * Plugin: Chart.js [https://www.chartjs.org/]
     *--------------------------------------------------------------*/
    const totalSongs = () => {
        const canvas = document.getElementById('total_songs');

        if (totalSongsChart) {
            totalSongsChart.destroy();
        }
        
        if (canvas) {
            const config = {
                type: 'bar',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                    datasets: [
                        {
                            label: 'Songs',
                            data: [65, 59, 42, 73, 56, 55, 40],
                            backgroundColor: Utils.getCSSVarValue('green'),
                            borderWidth: 0,
                            barThickness: 16,
                            pointRadius: 0
                        }
                    ]
                },
                options: {
                    title: {
                        display: false,
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                    elements: {
                        borderJoinStyle: 'bevel',
                        borderCapStyle: 'round',
                        line: {
                            tension: 0.4
                        }
                    },
                    scales: {
                        x: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false
                        },
                        y: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false,
                            min: 0,
                            max: 85
                        }
                    },
                    layout: {
                        margin: 0,
                        padding: 0
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                    }
                }
            };
            
            totalSongsChart = new Chart(canvas, config);
        }
    }

    /**
     * User chart
     * Plugin: Chart.js [https://www.chartjs.org/]
     *--------------------------------------------------------------*/
    const purchases = () => {
        const canvas = document.getElementById('purchases');

        if (purchasesChart) {
            purchasesChart.destroy();
        }
        
        if (canvas) {
            const config = {
                type: 'line',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                    datasets: [
                        {
                            label: 'Purchases',
                            data: [65, 59, 42, 73, 56, 55, 40],
                            backgroundColor: 'transparent',
                            borderColor: '#000000',
                            borderWidth: 2,
                            pointRadius: 0,
                            pointHoverRadius: 5,
                            pointHoverBorderWidth: 12,
                            pointBackgroundColor: Chart.helpers.color('#000000').alpha(0).rgbString(),
                            pointBorderColor: Chart.helpers.color('#000000').alpha(0).rgbString(),
                            pointHoverBackgroundColor: '#000000',
                            pointHoverBorderColor: Chart.helpers.color('#000000').alpha(0.1).rgbString(),
                        }
                    ]
                },
                options: {
                    title: {
                        display: false,
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                    elements: {
                        borderJoinStyle: 'bevel',
                        borderCapStyle: 'round',
                        line: {
                            tension: 0.4
                        }
                    },
                    scales: {
                        x: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false
                        },
                        y: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            display: false,
                            min: 0,
                            max: 85
                        }
                    },
                    layout: {
                        margin: 0,
                        padding: 0
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                    }
                }
            };
            
            purchasesChart = new Chart(canvas, config);
        }
    }

    /**
     * User chart
     * Plugin: Chart.js [https://www.chartjs.org/]
     *--------------------------------------------------------------*/
    const statistics = () => {
        const canvas = document.getElementById('user_statistics');
        
        if (statisticsChart) {
            statisticsChart.destroy();
        }
        
        if (canvas) {
            const config = {
                type: 'bar',
                data: {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                    datasets: [
                        {
                            label: 'Statistics',
                            data: [65, 59, 42, 73, 56, 55, 40],
                            backgroundColor: Utils.getCSSVarValue('purple'),
                            borderWidth: 0,
                            barThickness: 32,
                            pointRadius: 0
                        }
                    ]
                },
                options: {
                    title: {
                        display: false,
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                    elements: {
                        borderJoinStyle: 'bevel',
                        borderCapStyle: 'round',
                        line: {
                            tension: 0.4
                        }
                    },
                    scales: {
                        y: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            min: 0,
                            max: 80,
                            grid: {
                                borderColor: Utils.isDarkMode() ? '#34343e' : '#EFF2F5',
                            }
                        },
                        x: {
                            position: Utils.isRTL() ? 'right' : 'left',
                            grid: {
                                borderColor: Utils.isDarkMode() ? '#34343e' : '#EFF2F5',
                            }
                        }
                    },
                    layout: {
                        margin: 0,
                        padding: 0
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                    }
                }
            };
            
            statisticsChart = new Chart(canvas, config);
        }
    }

    return {
        init() {
            totalUsers();
            totalSongs();
            purchases();
            statistics();
        }
    }

}();

// Class initialization on page load
$(document).ready(function() {
    Dashboard.init();
});
"use strict";

// Class Definition
var Player = function () {

    var show = 'show';
    var active = 'active';

    var $body = $('body');
    var $playlist = $('#playlist');
    var songs = [];
    var playerConfig = Amplitude.getConfig();
    var mediaControls = {
        playPause: false,
        nextPrev: false
    };

    /**
     * Bind local storage songs with player
     *--------------------------------------------------------------*/
    var player = function() {
        if (Utils.getLocalItem('songs') && Utils.getLocalItem('songs').length) {
            songs = Utils.getLocalItem('songs');

            initPlayer(false);
            setPlayerPaused(); // Add and remove class to player play pause button
            
            if (songs.length > 1) {
                for (let i = 0; i < songs.length; i++) {
                    var song = songs[i];
                    if (i === 0) {
                        $playlist.html(playlistItem(song));
                    } else {
                        $playlist.append(playlistItem(song));
                    }
                }
            }
        }
    }

    /**
     * Play pause song
     *--------------------------------------------------------------*/
    var playPause = function() {
        $body.on('click', '[data-play-id]', function () {
            var song = getSongObject(this);
            var index = songs.findIndex(item => item.id === song.id);

            // Check song status is pause
            if ($(this).hasClass(active)) {
                Amplitude.pause();
                setPlayerPaused(); // Add and remove class to player play pause button

            } else {
                // Add song if not exist
                if (index === -1) {
                    songs.push(song);

                    // Initialize player
                    if (songs.length === 1) {
                        initPlayer();
                    } else {
                        $playlist.append(playlistItem(song));
                        Amplitude.playSongAtIndex(songs.length - 1);
                    }

                } else { // Play exist song
                    Amplitude.playSongAtIndex(index);
                }
            }

            Utils.setLocalItem('songs', songs);
        });
    }

    /**
     * Add song in playlist
     *--------------------------------------------------------------*/
    var addToQueue = function() {
        $body.on('click', '[data-queue-id]', function () {
            var song = getSongObject(this);
            var index = songs.findIndex(item => item.id === song.id);

            // Add song if not exist
            if (index === -1) {
                songs.push(song);
                songs.length === 1 ? initPlayer() : $playlist.append(playlistItem(song));

            } else { // Show message exist song
                Utils.showMessage('Song already in Queue');
            }

            Utils.setLocalItem('songs', songs);
        });
    }

    /**
     * Add song in playlist for play next
     *--------------------------------------------------------------*/
    var nextToPlay = function() {
        $body.on('click', '[data-next-id]', function () {
            var song = getSongObject(this);
            var activeIndex = Amplitude.getActiveIndex();
            var index = songs.findIndex(item => item.id === song.id);
            
            if (songs && !songs.length) {
                songs.push(song);
                initPlayer();

            } else {
                // Add song if not exist
                if (index === -1) {
                    songs.splice(activeIndex + 1, 0, song);
                    $playlist.find('.list__item').eq(activeIndex).after(playlistItem(song));

                } else { // Show message exist song
                    Utils.showMessage('Song already in Queue');
                }
            }

            Utils.setLocalItem('songs', songs);
        });
    }

    /**
     * Play all songs in list
     *--------------------------------------------------------------*/
     var playAll = function() {
        $body.on('click', '[data-collection-play-id]', function () {
            var id = $(this).attr('data-collection-play-id');
            var $el = $('[data-collection-song-id=' + id + ']');
            var $list = $el.find('[data-song-id]');
            var songList = [];
            var index = 0;

            $list.each(function() {
                var song = getSongObject(this);
                songList.push(song);
            });

            if (songs && !songs.length) {
                songs = songList;
                initPlayer();
                index = 1;

            } else {
                songs.push(...songList);
            }

            for (let i = index; i < songList.length; i++) {
                $playlist.append(playlistItem(songList[i]));
            }
            
            Utils.setLocalItem('songs', songs);
        });
    }

    /**
     * Remove song from playlist
     *--------------------------------------------------------------*/
    var removeSong = function() {
        $body.on('click', '[data-remove-song-id]', function (e) {
            e.stopPropagation();

            var id = parseInt($(this).data('remove-song-id'));
            var $item = $(this).closest('[data-song-id');
            var index = songs.findIndex(song => song.id === id);

            if (index > -1) {
                $item.remove();
                Amplitude.removeSong(index);
                if (songs.length === 0) {
                    emptyPlaylist();
                }
            }

            Utils.setLocalItem('songs', songs);
        });
    }

    /**
     * Clear song from playlist
     *--------------------------------------------------------------*/
    var clearPlaylist = function() {
        $('#clear_playlist').on('click', function () {
            if (songs.length >= 1) {
                // Remove songs from list
                for (var i = 0; i < songs.length; i++) {
                    $playlist.find('.list__item').eq(i).remove();
                }

                // Remove songs from player
                for (var i = songs.length - 1; i > -1; i--) {
                    var song = songs[i];
                    var activeSong = Amplitude.getActiveSongMetadata();

                    if (song.id !== activeSong.id) {
                        Amplitude.removeSong(i);
                    }
                }

                // Set empty playlist view
                emptyPlaylist();
            }
        });
    }

    /**
     * Changed volume icon on its value
     *--------------------------------------------------------------*/
    var volume = function() {
        var $volume = $('.player-volume');
        var $input = $volume.find('input[type="range"]');

        $input.on('input', function () {
            var $mute = $volume.find('.ri-volume-mute-fill');
            var $down = $volume.find('.ri-volume-down-fill');
            var $up = $volume.find('.ri-volume-up-fill');
            var $this = $(this);
            
            var value = parseInt($this.val(), 10);
            var block = 'd-block';
            var none = 'd-none';

            // Change background
            Player.volumeBackground();
            
            // Change icon volume on input value
            if (value === 0) {
                $mute.removeClass(none).addClass(block);
                $down.addClass(none);
                $up.addClass(none);
            } else if (value > 0 && value < 70) {
                $mute.addClass(none);
                $down.removeClass(none).addClass(block);
                $up.addClass(none);
            } else if (value > 70) {
                $mute.addClass(none);
                $down.addClass(none);
                $up.removeClass(none).addClass(block);
            }
        })
    }

    /**
     * Player play pause event
     *--------------------------------------------------------------*/
    var playerEvent = function() {
        $('.amplitude-play-pause').on('click', function () {
            mediaSession();
            playerDelay(() => {
                Amplitude.getPlayerState() === 'playing' ? setPlayButtonView() : $('[data-play-id]').removeClass(active);
            });
        });

        $('.amplitude-prev').on('click', function() {
            playerConfig.player_state = 'playing';
        });

        $('.amplitude-next').on('click', function() {
            playerConfig.player_state = Amplitude.getActiveIndex() ? 'playing' : 'stopped';
        });
    }

    /**
     * Delay between song change
     *--------------------------------------------------------------*/    
    var playerDelay = function(callback) {
        setTimeout(callback, Amplitude.getDelay());
    }

    /**
     * Initialize player
     * @param {boolean} isPlay
     *--------------------------------------------------------------*/
    var initPlayer = function(isPlay = true) {
        // Show player UI
        $('#player').addClass(show);
        
        if (Amplitude.getSongs() && Amplitude.getSongs().length === 1) {
            Amplitude.pause();
            playerDelay(() => {
                Player.volumeBackground(); // Change volume input background
            });
        }

        // Init Amplitude plugin
        Amplitude.init({
            songs: songs,
            callbacks: {
                song_change: function() {
                    // Change play pause button view
                    playerDelay(() => {
                        mediaSession();
                        Amplitude.getPlayerState() === 'playing' ? setPlayButtonView() : $('[data-play-id]').removeClass(active);
                        changePlayerOptions(song); // Change player options when song changed
                    });
                }
            }
        });

        var song = songs[0];
        $playlist.html(playlistItem(song));
        disablePlayerControls(false);

        setPlayerPlaying(); // Add and remove class to player play pause button
        
        if (isPlay) {
            Amplitude.play();
            setPlayButtonView();
            mediaSession();
        }

        // Change player options when song changed
        changePlayerOptions(song);
    }

    /**
     * Get song object to bind with player
     * @param {object} the 
     * @returns {object}
     *--------------------------------------------------------------*/
    var getSongObject = function(the) {
        var element = $(the).closest('[data-song-id]');
        return {
            id: parseInt(element.data('song-id')),
            name: element.data('song-name'),
            artist: element.data('song-artist'),
            album: element.data('song-album'),
            url: element.data('song-url'),
            cover_art_url: element.data('song-cover')
        };
    }

    /**
     * Changed active song option that bind with player
     * @param {object} song 
     *--------------------------------------------------------------*/
    var changePlayerOptions = function(song) {
        var $options = $('#player_options');

        $options.find('[data-favorite-id]').attr('data-favorite-id', song.id);
        $options.find('[data-playlist-id]').attr('data-playlist-id', song.id);
        $options.find('[download]').attr('href', song.url);
    }

    /**
     * Set player button disable attribute
     * @param {boolean} disabled 
     *--------------------------------------------------------------*/
    var disablePlayerControls = function(disabled = true) {
        $('.amplitude-repeat, .amplitude-prev, .amplitude-next, .amplitude-shuffle').prop('disabled', disabled);
    }

    /**
     * Playlist song item view
     * @param {object} song 
     * @returns {string}
     *--------------------------------------------------------------*/
    var playlistItem = function(song) {
        var activeSong = Amplitude.getActiveSongMetadata();
        var isActive = () => {
            return Amplitude.getPlayerState() === 'playing' && song.id === activeSong.id;
        }

        return `<div class="list__item"
        data-song-id="${song.id}"
        data-song-name="${song.name}"
        data-song-artist="${song.artist}"
        data-song-album="${song.album}"
        data-song-url="${song.url}"
        data-song-cover="${song.cover_art_url}">
            <div class="list__cover">
                <img src="${song.cover_art_url}" alt="${song.name}">
                <a href="javascript:void(0);" class="btn btn-play btn-sm btn-default btn-icon rounded-pill ${isActive() ? 'active' : ''}" data-play-id="${song.id}">
                    <i class="ri-play-fill icon-play"></i>
                    <i class="ri-pause-fill icon-pause"></i>
                </a>
            </div>
            <div class="list__content">
                <a href="song-details.html" class="list__title text-truncate">${song.name}</a>
                <p class="list__subtitle text-truncate">
                    <a href="artist-details.html">${song.artist}</a>
                </p>
            </div>
            <ul class="list__option">
                <li class="list__icon-hover">
                    <a role="button" class="d-inline-flex" data-remove-song-id="${song.id}">
                        <i class="ri-close-line fs-6"></i>
                    </a>
                </li>
                <li>
                    <a role="button" class="d-inline-flex" data-favorite-id="${song.id}">
                        <i class="ri-heart-line heart-empty"></i>
                        <i class="ri-heart-fill heart-fill"></i>
                    </a>
                </li>
            </ul>
        </div>`;
    }

    /**
     * Empty playlist view
     *--------------------------------------------------------------*/
    var emptyPlaylist = function() {
        // Set empty playlist view
        songs = [];
        disablePlayerControls();

        Amplitude.pause();
        playerConfig.player_state = 'paused';

        // Remove songs from local storage
        Utils.removeLocalItem('songs');

        $playlist.html(`<div class="col-sm-8 col-10 mx-auto mt-5 text-center">
            <i class="ri-music-2-line mb-3"></i>
            <p>No songs, album or playlist are added on lineup.</p>
        </div>`);

        playerDelay(() => {
            setPlayerPaused();
            mediaSession();
        });
    }

    /**
     * Global play/pause button view
     *--------------------------------------------------------------*/
    var setPlayButtonView = function() {
        var song = Amplitude.getActiveSongMetadata();
        $('[data-play-id]').removeClass(active);
        $('[data-play-id=' + song.id + ']').addClass(active);
    }

    /**
     * Player play button view
     *--------------------------------------------------------------*/
    var setPlayerPlaying = function() {
        $('.amplitude-play-pause').removeClass('amplitude-paused').addClass('amplitude-playing');
    }

    /**
     * Player pause button view
     *--------------------------------------------------------------*/
    var setPlayerPaused = function() {
        $('.amplitude-play-pause').removeClass('amplitude-playing').addClass('amplitude-paused');
        $('[data-play-id]').removeClass(active);
    }

    /**
     * Set media session
     *--------------------------------------------------------------*/
    var mediaSession = function() {
        var song = Amplitude.getActiveSongMetadata();
        var playlist = Amplitude.getActivePlaylist() ? Amplitude.getActivePlaylist() : '';

        // Media play onclick
        var mediaPlay = function() {
            Amplitude.play();
            setPlayerPlaying();
            setPlayButtonView();
        }

        // Media pause onclick
        var mediaPause = function() {
            Amplitude.pause();
            setPlayerPaused();
        }

        if ('mediaSession' in navigator) {
            var MEDIA = navigator.mediaSession;
            // Set song meta on notification
            MEDIA.metadata = new MediaMetadata({
                title: song.name,
                artist: song.artist,
                album: song.album,
                artwork: [
                    { src: song.cover_art_url, sizes: '96x96', type: 'image/jpg' },
                    { src: song.cover_art_url, sizes: '128x128', type: 'image/jpg' },
                    { src: song.cover_art_url, sizes: '192x192', type: 'image/jpg' },
                    { src: song.cover_art_url, sizes: '256x256', type: 'image/jpg' },
                    { src: song.cover_art_url, sizes: '384x384', type: 'image/jpg' },
                    { src: song.cover_art_url, sizes: '512x512', type: 'image/jpg' },
                ]
            });

            if (songs.length >= 1 && !mediaControls.playPause) {
                mediaControls.playPause = true;
                MEDIA.setActionHandler('play', () => mediaPlay());
                MEDIA.setActionHandler('pause', () => mediaPause());
            }

            if (songs.length >= 2 && !mediaControls.nextPrev) {
                mediaControls.nextPrev = true;
                MEDIA.setActionHandler('previoustrack', () => Amplitude.prev(playlist));
                MEDIA.setActionHandler('nexttrack', () => Amplitude.next(playlist));
            }
        }
    }

    return {
        /**
         * Set volume slider background style
         *--------------------------------------------------------------*/
        volumeBackground: function() {
            var $volume = $('.player-volume input[type="range"]');
            var value = parseInt($volume.val(), 10);

            // Change input background gradient
            var color = Utils.isDarkMode() ? '255, 255, 255' : Utils.getCSSVarValue('dark-rgb');
            var gradient = 'linear-gradient(to right, rgb(' +
                color + ') 0%, rgb(' + color + ') ' + value + '%, rgba(' + 
                color + ', 0) ' + value + '%, rgba(' + color + ', 0) 100%)';

            $volume.css('background', gradient);
        },

        init: function() {
            player();
            playPause();
            addToQueue();
            nextToPlay();
            playAll();
            removeSong();
            clearPlaylist();
            volume();
            playerEvent();
        }
    }

}();


// Class initialization on page load
$(document).ready(function() {
    Player.init();
});
/**
 * Theme Settings v1.0.0
 * Copyright 2019 Kri8thm
 * Licensed under MIT
 *------------------------------------*/

(function ($, window, document, undefined) {

    var body = document.body;
    var dataAttr = {
        theme: 'data-theme',
        header: 'data-header',
        sidebar: 'data-sidebar',
        player: 'data-player',
    }
    
    $.fn.extend({
        settings: function(options) {
            options = $.extend({}, $.settings.defaults, options);

            // this creates a plugin for each element in the selector or runs the function once per selector.
            this.each(function() {
                new $.settings(this, options);
            });
            return;
        }
    });

    $.settings = function(element, options) {        
        var skin = 'skin';
        // settings constant config
        var config = {
            name: 'setting',
            title: 'Theme Settings',
            colors: ['red', 'green', 'blue', 'orange', 'yellow', 'purple', 'indigo', 'pink', 'violet', 'magenta'],
            theme: ['light', 'dark'],
        }

        var init = (force = false) => {
            var header = document.getElementById('header');
            var sidebar = document.getElementById('sidebar');
            var player = document.getElementById('player');
            
            var dark = 'dark';
            var skinObj = {
                dark: options.dark,
                system: options.system,
                rtl: options.rtl,
                header: options.header,
                sidebar: options.sidebar,
                player: options.player
            }

            if (
                !force &&
                !skinObj.dark &&
                !skinObj.system &&
                body.hasAttribute(dataAttr.theme)
            ) {
                skinObj.dark = true;
            }

            if (document.dir == "rtl") {
                skinObj.rtl = true;
            }

            // Set object in local storage
            Utils.setLocalItem(skin, skinObj);
            skinObj.dark ? body.setAttribute(dataAttr.theme, dark) : body.removeAttribute(dataAttr.theme);
            skinObj.rtl 
            ? document.documentElement.setAttribute('dir', 'rtl') 
            : document.documentElement.removeAttribute('dir');

            if (header && options.header) {
                header.setAttribute(dataAttr.header, options.header);
            }

            if (sidebar && options.sidebar) {
                sidebar.setAttribute(dataAttr.sidebar, options.sidebar);
            }

            if (player && options.player) {
                player.setAttribute(dataAttr.player, options.player);
            }
        };

        var optionsTemplate = (list, label, name) => {
            var optionHtml = `<div class="${config.name}__card__item"><span class="${config.name}__title">${label}</span>
                <div class="${config.name}__options">`;
        
            for (let i = 0; i < list.length; i++) {
                var color = list[i];
                optionHtml += `<a role="button" aria-label="Color ${color}"
                    class="${config.name}__option ${config.name}__option--${color} ${(!options[name] && color === 'blue') && 'active'}" 
                    data-${name}-option="${color}"></a>`
            }

            optionHtml += `</div></div>`;
            return optionHtml;
        }

        // events for settings option
        var events = () => {
            var setting = $(`#${config.name}`);
            var toggler = $(`#${config.name}_toggler`);
            var close = $(`#${config.name}_close`);
            var link = $(`.${config.name}__option`);
            var show = 'show';
            var active = 'active';

            $(body).on('click', () => {
                setting.removeClass(show);
            });

            toggler.on('click', () => {
                setting.toggleClass(show);
            });

            close.on('click', () => {
                setting.removeClass(show);
            });

            setting.on('click', (e) => {
                e.stopPropagation();
            });
            
            link.on('click', function() {
                var $this = $(this);
                var theme = $this.data('theme-option');
                var headerTheme = $this.data('header-option');
                var sidebarTheme = $this.data('sidebar-option');
                var playerTheme = $this.data('player-option');
                var rtl = $this.data('rtl-option');
                
                if (theme) {
                    $('[data-theme-option]').removeClass(active);
                    if (theme === 'system') {
                        options.system = true;
                        options.dark = false;
                        if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
                            options.dark = true;
                        }
                        
                    } else {
                        options.system = false;
                        theme === 'dark' ? options.dark = true : options.dark = false;
                    }

                    Utils.changeSkin();

                } else if (rtl) {
                    window.location.reload();
                    $this.is(":checked") 
                    ? document.documentElement.setAttribute('dir', 'rtl') 
                    : document.documentElement.removeAttribute('dir');
                    options.rtl = $this.is(":checked");

                } else if (headerTheme) {
                    $('[data-header-option]').removeClass(active);
                    options.header = headerTheme;

                } else if (sidebarTheme) {
                    $('[data-sidebar-option]').removeClass(active);
                    options.sidebar = sidebarTheme;

                } else if (playerTheme) {
                    $('[data-player-option]').removeClass(active);
                    options.player = playerTheme;
                }

                $this.addClass(active);
                init(true);
            });
        };

        var initAttr = () => {
            if (options.system) {
                $('[data-theme-option="system"]').addClass('active');
            } else {
                options.dark 
                ? $('[data-theme-option="dark"]').addClass('active') 
                : $('[data-theme-option="light"]').addClass('active');
            }
            $('[data-header-option=' + options.header + ']').addClass('active');
            $('[data-sidebar-option=' + options.sidebar + ']').addClass('active');
            $('[data-player-option=' + options.player + ']').addClass('active');
        };

        (() => {
            var obj = Utils.getLocalItem(skin);
            if (obj) {
                options = $.extend({}, options, obj);
            }
            
            init();
            template();
            initAttr();
        })();
    };

    // setting default options
    $.settings.defaults = {
        dark: false,
        system: false,
        rtl: false,
        header: null,
        sidebar: null,
        player: null,
    };

})(jQuery, window, document);

"use strict";

// Class Definition
const Utils = function() {

    return {

        /**
         * CSS variable value
         * @param {string} name 
         * @returns {string}
         *--------------------------------------------------------------*/
        getCSSVarValue(name) {
            var hex = getComputedStyle(document.documentElement).getPropertyValue('--bs-' + name);
            if (hex && hex.length > 0) {
                hex = hex.trim();
            }

            return hex;
        },

        /**
         * Get local storage item by name
         * @param {string} name 
         * @returns {object}
         *--------------------------------------------------------------*/
        getLocalItem(name) {
            return JSON.parse(localStorage.getItem(name));
        },

        /**
         * Set local storage item
         * @param {string} name 
         * @param {object} obj 
         *--------------------------------------------------------------*/
        setLocalItem(name, obj) {
            localStorage.setItem(name, JSON.stringify(obj));
        },

        /**
         * Remove local storage item
         * @param {string} name
         *--------------------------------------------------------------*/
        removeLocalItem(name) {
            localStorage.removeItem(name);
        },

        /**
         * Show sanckbar message
         * @param {string} message
         *--------------------------------------------------------------*/
        showMessage(message) {
            Snackbar.show({
                pos: this.isRTL() ? 'bottom-left' : 'bottom-right',
                text: message,
                showAction: false
            });
        },

        /**
         * Switch skin 
         *--------------------------------------------------------------*/
        changeSkin() {
            setTimeout(() => {
                ChartJs.overrideDefaults();
                Dashboard.init();
                Player.volumeBackground();
            }, 10);
        },

        /**
         * Check dark mode
         *--------------------------------------------------------------*/
        isDarkMode() {
            return (document.querySelector('body').getAttribute("data-theme") === 'dark');
        },

        /**
         * Check right to left
         *--------------------------------------------------------------*/
        isRTL() {
            return (document.querySelector('html').getAttribute('dir') === 'rtl');
        }
    }

}();


// Webpack support
if (typeof module !== 'undefined' && typeof module.exports !== 'undefined') {
    module.exports = Utils;
}