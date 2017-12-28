module.exports = function (grunt) {
	var path={
		base_dest:'product/dest/webapp/resources',
		base:'${resources_static}'
	}
   grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
	config :{ 
		base_cwd:'product/cwd/webapp',base_dest:'product/dest/webapp', 
		static_cwd:'product/cwd/webapp/resources', static_dest:'product/dest/webapp/resources'
	 },
	clean: {
        release: {
				src: ['<%= config.base_dest %>/']
        }
    },
	copy: {
        page: {
          files: [{
			expand: true, 
			cwd: '<%= config.base_cwd %>', 
			src: ['**/*.*','!**/*.css','!**/*.js','**/*.min.css','**/*.min.js','**/Gruntfile.js'],  
			dest: '<%= config.base_dest %>'}
          ]
		}
    },
	cssmin: {
      all: {
        expand: true,
        cwd: '<%= config.static_cwd %>',
        src: ['**/*.css','!**/*.min.css'],
        dest: '<%= config.static_dest %>',
      }
    },
	uglify:{
		options:{
			banner:'/*! <%= grunt.template.today("yyyy-mm-dd HH:MM") %> */\n'
		},
		my_target:{
			files:[
				{
					expand:true,
					cwd:'<%=  config.static_cwd %>',
					src:['**/*.js','!**/*.min.js'],
					dest:'<%= config.static_dest %>'
				}
			]
		}
	},
	imagemin: {
		/* 压缩图片大小 */
		dist: {
			options: {
				optimizationLevel: 3 //定义 PNG 图片优化水平
			},
			files: [
				   {
				expand: true,
				cwd: '<%= config.static_cwd %>',
				src: ['**/*.{png,jpg,JPG,jpeg,gif}'], // 优化 img 目录下所有 png/jpg/jpeg 图片
				dest: '<%= config.static_dest  %>' // 优化后的图片保存位置，覆盖旧图片，并且不作提示
				}
			]
		}
	},
	filerev: {
		options: {encoding: 'utf8',algorithm: 'md5',length: 8},
		assets: {
			files: [{
				src: [
					'<%= config.static_dest %>/**/*.css',
					'<%= config.static_dest %>/**/*.js',
					'!<%= config.static_dest %>/**/WdatePicker.css',
					
				]
			}]
		}
	},
	usemin: {
		options: {
			filePrefixer:function(url){
				if(!url){
					return '';
				}
				var jsFlg=url.length-'.js'.length;
				var cssFlg=url.length-'.css'.length;
				if((jsFlg>=0&&url.lastIndexOf('.js')==jsFlg)||(cssFlg>=0&&url.lastIndexOf('.css')==cssFlg)){
					var fileName=url.replace(path.base,path.base_dest).replace(/\//g, require('path').sep);
					var temp=grunt.filerev.summary[fileName];
					if(temp!=undefined){
						temp=temp.replace(path.base_dest.replace(/\//g, require('path').sep),path.base)
					}
					return url.replace(url,temp);
				}else{
					 return url 
				}
            }
		},
		html: ['<%= config.base_dest %>/**/*.ftl','<%= config.base_dest %>/**/*.jsp']
    }
  });
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-imagemin');
  grunt.loadNpmTasks('grunt-filerev');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-levin-usemin');
  grunt.loadNpmTasks('grunt-rev');
  // 默认任务
  grunt.registerTask('default', ['clean','copy','cssmin','uglify','filerev','usemin']);
}
